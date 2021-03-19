package com.zero.travel.service.backend;

import com.zero.travel.common.util.SystemUtils;
import com.zero.travel.mapper.RouteMapper;
import com.zero.travel.pojo.dto.RouteDTO;
import com.zero.travel.pojo.dto.UploadDTO;
import com.zero.travel.pojo.entity.Route;
import com.zero.travel.service.common.UploadService;

import org.apache.commons.beanutils.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StreamUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;

/**
 * 业务层-旅游线路
 * @author LJC
 * @version 1.0
 * @date 2021/3/18 14:27
 */
@Service
@Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
public class RouteService {

    @Autowired
    private Environment env;

    @Autowired
    private UploadService uploadService;

    @Autowired
    private RouteMapper routeMapper;

    private static final Logger log = LoggerFactory.getLogger(RouteService.class);

    /**
     * 新增旅游线路
     * @param routeDTO
     */
    public void add(RouteDTO routeDTO) throws Exception {
        //TODO:上传图片
        final MultipartFile file = routeDTO.getImageFile();

        //相对路径写法
        ///final String rootPath = ResourceUtils.getFile("classpath:image/route/").getPath();

        String rootPath = env.getProperty("upload.root.location");
        final String fileName = "image/route/"+SystemUtils.rename(file.getOriginalFilename());
        log.info("imageUrl:{}",fileName);

        UploadDTO uploadDTO = new UploadDTO();
        uploadDTO.setFile(file);
        uploadDTO.setRootPath(rootPath);
        uploadDTO.setFileName(fileName);
        //上传
        try {
            uploadService.commonUpload(uploadDTO);
        } catch (Exception e) {
            log.error("文件上传异常:{}",e.getMessage());
            throw new FileNotFoundException("文件上传失败");
        }

        //TODO:记录到数据库
        String imageUrl = fileName;
        routeDTO.setImageUrl(imageUrl);
        Route route = new Route();
        BeanUtils.copyProperties(routeDTO,route);

        routeMapper.insertSelective(route);

    }

    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public List<Route> findAll() {
        List<Route> routeList = routeMapper.selectAll();

        return routeList;
    }

    /**
     * 获取线路图片
     * @param path
     * @param outputStream
     */
    public void getImage(String path, OutputStream outputStream) throws IOException {
        //相对路径写法
        ///final String rootPath = ResourceUtils.getFile("classpath:image/route/").getPath();

        String rootPath = env.getProperty("upload.root.location");
        String fileName = rootPath +File.separator+path;
        log.info("[加载图片]物理路径:{}",fileName);

        //将图片加载到输出流中图片
        StreamUtils.copy(new FileInputStream(fileName),outputStream);

    }

    /**
     * 修改线路信息
     * @param routeDTO
     */
    public void modify(RouteDTO routeDTO) throws Exception {
        //TODO:判断图片是否修改 文件名不为空时说明图片以及更新
        final MultipartFile imageFile = routeDTO.getImageFile();
        final String filename = "image/route/"+SystemUtils.rename(imageFile.getOriginalFilename());

        final Route oldRoute = routeMapper.selectByPrimaryKey(routeDTO.getRouteId());
        Route newRoute = new Route();

        if (imageFile.getOriginalFilename()!=null && !"".equals(imageFile.getOriginalFilename())){
            //TODO: 删除原图片
            String rootPath = env.getProperty("upload.root.location");
            String oldImage = rootPath +File.separator+ oldRoute.getImageUrl();
            File file = new File(oldImage);
            System.gc();
            boolean flag = file.delete();
            log.info("原图片是否删除:{}",flag);

            //TODO:上传图片
            UploadDTO uploadDTO = new UploadDTO();
            uploadDTO.setFile(imageFile);
            uploadDTO.setFileName(filename);
            uploadDTO.setRootPath(rootPath);

            try {
                uploadService.commonUpload(uploadDTO);
            } catch (Exception e) {
                log.error("文件上传异常:{}",e.getMessage());
                throw new FileNotFoundException("文件上传失败");
            }


        }
        //TODO:更新数据库
        BeanUtils.copyProperties(routeDTO,newRoute);
        if (imageFile.getOriginalFilename()!=null && !"".equals(imageFile.getOriginalFilename())){
            //图片发生变更时保存新的imageUrl
            newRoute.setImageUrl(filename);
        }

        int row = routeMapper.updateByPrimaryKeySelective(newRoute);
        if (row != 1){
            throw new RuntimeException("修改旅游线路失败");
        }
    }

    /**
     * 删除
     * @param routeId
     */
    public void remove(Integer routeId) {
        //TODO: 清除数据库记录
        final Route route = routeMapper.selectByPrimaryKey(routeId);
        if (route == null){
            throw new RuntimeException("旅游线路不存在");
        }
        routeMapper.deleteByPrimaryKey(routeId);

        //TODO: 删除图片
        String rootPath = env.getProperty("upload.root.location");
        deleteImage(route.getImageUrl(),rootPath);
    }

    /**
     * 删除图片
     * @param imageUrl
     */
    public static void deleteImage(String imageUrl,String rootPath){
        String oldImage = rootPath +File.separator+ imageUrl;
        File file = new File(oldImage);
        System.gc();
        boolean flag = file.delete();
        log.info("[删除线路信息]图片物理路径:{}",oldImage);
        log.info("原图片是否删除:{}",flag);
    }
}
