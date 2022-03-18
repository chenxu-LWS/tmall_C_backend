package com.tmall_backend.bysj.service.commodity_picture;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.OSSObject;
import com.tmall_backend.bysj.common.constants.ErrInfo;
import com.tmall_backend.bysj.common.exception.BizException;
import com.tmall_backend.bysj.common.oss.OssClient;
import com.tmall_backend.bysj.entity.CommodityPicture;
import com.tmall_backend.bysj.mapper.CommodityMapper;
import com.tmall_backend.bysj.mapper.CommodityPictureMapper;

/**
 * @author LiuWenshuo
 * Created on 2022-03-09
 */
@Service
public class CommodityPictureService {
    Logger logger = Logger.getLogger(CommodityPictureService.class);
    @Autowired
    CommodityPictureMapper pictureMapper;
    @Autowired
    CommodityMapper commodityMapper;

    @Autowired
    OssClient ossClient;

    private static final String MAIN_BUCKET_NAME = "tmall-commodity-pictures";
    private static final String DETAIL_BUCKET_NAME = "tmall-commodity-details-pictures";


    public Integer insertPic(CommodityPicture picture) {
        pictureMapper.insertPic(picture);
        return picture.getId();
    }

    public List<String> queryMainPicByCommodityId(Integer commodityId) throws BizException {
        if (commodityMapper.queryCommodityById(commodityId) == null) {
            throw new BizException(ErrInfo.COMMODITY_ID_NOT_EXISTS);
        }
        try {
            final List<CommodityPicture> commodityPictures = pictureMapper.queryMainPicByCommodityId(commodityId);
            List<String> pictures = new ArrayList<>();
            for (CommodityPicture commodityPicture : commodityPictures) {
                final String pictureObj = commodityPicture.getPictureObj();
                final OSSObject ossObject = ossClient.getOssClient().getObject(MAIN_BUCKET_NAME, pictureObj);
                BufferedReader reader = new BufferedReader(new InputStreamReader(ossObject.getObjectContent()));
                StringBuilder builder = new StringBuilder();
                while (true) {
                    String line = reader.readLine();
                    if (line == null) {
                        break;
                    }
                    builder.append(line);
                }
                reader.close();
                pictures.add(builder.toString());
            }
            return pictures;
        } catch (OSSException oe) {
            logger.error("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            logger.error("Error Message:" + oe.getErrorMessage());
            logger.error("Error Code:" + oe.getErrorCode());
            logger.error("Request ID:" + oe.getRequestId());
            logger.error("Host ID:" + oe.getHostId());
            throw new BizException(ErrInfo.OSS_ERROR);
        } catch (Throwable ce) {
            logger.error("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            logger.error("Error Message:" + ce.getMessage());
            throw new BizException(ErrInfo.OSS_ERROR);
        }
    }

    public List<String> queryDetailPicByCommodityId(Integer commodityId) {
        if (commodityMapper.queryCommodityById(commodityId) == null) {
            throw new BizException(ErrInfo.COMMODITY_ID_NOT_EXISTS);
        }
        try {
            final List<CommodityPicture> commodityPictures = pictureMapper.queryDetailPicByCommodityId(commodityId);
            List<String> pictures = new ArrayList<>();
            for (CommodityPicture commodityPicture : commodityPictures) {
                final String pictureObj = commodityPicture.getPictureObj();
                final OSSObject ossObject = ossClient.getOssClient().getObject(DETAIL_BUCKET_NAME, pictureObj);
                BufferedReader reader = new BufferedReader(new InputStreamReader(ossObject.getObjectContent()));
                StringBuilder builder = new StringBuilder();
                while (true) {
                    String line = reader.readLine();
                    if (line == null) {
                        break;
                    }
                    builder.append(line);
                }
                reader.close();
                pictures.add(builder.toString());
            }
            return pictures;
        }catch (OSSException oe) {
            logger.error("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            logger.error("Error Message:" + oe.getErrorMessage());
            logger.error("Error Code:" + oe.getErrorCode());
            logger.error("Request ID:" + oe.getRequestId());
            logger.error("Host ID:" + oe.getHostId());
            throw new BizException(ErrInfo.OSS_ERROR);
        } catch (Throwable ce) {
            logger.error("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            logger.error("Error Message:" + ce.getMessage());
            throw new BizException(ErrInfo.OSS_ERROR);
        }
    }
}
