package com.lee.bussi.service.impl;

import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.lee.bussi.form.BusCarsForm;
import com.lee.bussi.query.BusCarsQuery;
import com.lee.bussi.vo.BusCarsVO;
import com.lee.common.CodeMsg;
import com.lee.common.Constant;
import com.lee.common.Result;
import com.lee.common.base.mapper.BaseMapper;
import com.lee.common.base.service.IBaseService;
import com.lee.common.base.service.impl.BaseServiceImpl;
import com.lee.common.exception.BussinessException;
import com.lee.common.util.WebUtil;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.validation.constraints.Max;

import com.lee.bussi.mapper.BusCarsMapper;
import com.lee.bussi.pojo.BusCars;
import com.lee.bussi.service.BusCarsService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.channels.NonWritableChannelException;
import java.util.List;
import java.util.UUID;

@Service
public class BusCarsServiceImpl extends BaseServiceImpl<BusCarsForm> implements BusCarsService{

    @Resource
    private BusCarsMapper busCarsMapper;

    public BusCarsServiceImpl(BusCarsMapper baseMapper) {
        super(baseMapper);
    }

    @Override
    public int insert(BusCars record) {
        return busCarsMapper.insert(record);
    }
    @Override
    public int insertSelective(BusCars record) {
        return busCarsMapper.insertSelective(record);
    }

    @Override
    @Transactional
    public Result addCar(MultipartFile file, BusCarsForm form)  {
        /**
         *  进行业务数据校验
         *  车牌号不能重复
         *  将相关数据存储到数据库中
         *
         *  先保存数据，如果图片保存出了 问题，数据保存失败，图片保存没有任何意义   */
        BusCarsQuery query   = new BusCarsQuery();
        //校验车牌号是否被使用
        query.setCarNum(form.getCarNum());
        List<BusCarsVO> data = busCarsMapper.selectList(query);
        if(data!=null &&  !data.isEmpty()){
            return  new Result(CodeMsg.CAR_CAR_NUM_EXIST_ERROR);
        }
        //添加数据
        String realName  = file.getOriginalFilename();
        UUID uuid = UUID.randomUUID();
        //根据uuid和realName生成保存文件的名称
        String fileName = uuid +realName.substring(realName.lastIndexOf("."));

        //获取文件的存绝对路径
        ServletContext servletContext = WebUtil.getRequst().getServletContext();
        //保存文件的文件夹路径
        String parentPath = servletContext.getRealPath(Constant.FILE_ABSOLUT_PATH);
        File parentFile  = new File(parentPath);
        if(!parentFile.exists()){
            /*不存在文件夹则创建文件夹*/
            parentFile.mkdirs();
        }
        //文件的保存路径
        String fileAbsolutPath  =  parentPath + File.separator+fileName;
        //保存文件的url地址，用于页面显示
        String fieUrl  = Constant.FILE_ABSOLUT_PATH + fileName;

        form.setImg(fieUrl);
        form.setIsRent(Constant.CAR_NOT_RENT);
        //存入数据库
        busCarsMapper.insert(form);

        try {
            file.transferTo(new File(fileAbsolutPath));
        } catch (IOException e) {
            e.printStackTrace();
            //抛异常  回滚数据
            throw  new BussinessException(CodeMsg.CAR_CAR_IMG_SAVE_ERROR.code,CodeMsg.CAR_CAR_IMG_SAVE_ERROR.msg);
        }


        return new Result();
    }



}
