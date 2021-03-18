package com.zero.travel.service.backend;

import com.zero.travel.common.util.SystemUtils;
import com.zero.travel.mapper.RouteMapper;
import com.zero.travel.pojo.dto.RouteDTO;
import com.zero.travel.pojo.dto.UploadDTO;
import com.zero.travel.pojo.entity.Route;
import com.zero.travel.service.common.UploadService;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
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
        final String fileName = SystemUtils.rename(file.getOriginalFilename());

        UploadDTO uploadDTO = new UploadDTO();
        uploadDTO.setFile(file);
        uploadDTO.setRootPath(rootPath);
        uploadDTO.setFileName(fileName);
        //上传
        UploadService.commonUpload(uploadDTO);

        //TODO:记录到数据库
        String imageUrl = "route\\image"+File.separator+fileName;
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
}
