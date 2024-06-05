package com.witch.pan.redis;

import com.witch.pan.entity.constants.Constants;
import com.witch.pan.entity.dto.DownloadFileDTO;
import com.witch.pan.entity.dto.SysSettingsDTO;
import com.witch.pan.entity.dto.UserSpaceDTO;
import com.witch.pan.entity.vo.SessionWebUserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * redis数据操作类
 * @author Yuuki
 */

@Component("redisComponent")
public class RedisComponent {

    @Autowired
    private RedisUtils<Object> redisUtils;

    //拿取邮件固定信息和用户总使用空间
    public SysSettingsDTO getSysSettingDto() {
          SysSettingsDTO sysSettingsDTO = (SysSettingsDTO)redisUtils.get(Constants.REDIS_KEY_SYS_SETTING);
          if(sysSettingsDTO == null) {
                sysSettingsDTO = new SysSettingsDTO();
                redisUtils.set(Constants.REDIS_KEY_SYS_SETTING, sysSettingsDTO);
          }
          return sysSettingsDTO;
    }

    public void saveSysSettingDto(SysSettingsDTO dto) {
        redisUtils.set(Constants.REDIS_KEY_SYS_SETTING, dto);
    }

    public void saveUserSpaceUse(String userId, UserSpaceDTO userSpaceDto) {
        //1天时限
        redisUtils.setex(Constants.REDIS_KEY_USER_SPACE_USE + userId, userSpaceDto, Constants.REDIS_KEY_EXPIRE_ONE_DAY);
    }

    public Long getInitSpace() {
        return getSysSettingDto().getUserInitSpace() * Constants.GB;
    }

    public UserSpaceDTO getUserSpaceUse(String userId) {
        return (UserSpaceDTO) redisUtils.get(Constants.REDIS_KEY_USER_SPACE_USE + userId);
    }

    public void saveFileTempSize(String userId, String fileId, Long fileSize) {
        Long currentSize = getFileTempSize(userId, fileId);
        String key = Constants.REDIS_KEY_USER_FILE_TEMP_SIZE + userId + fileId;
        //暂存时间长点，不然超时没下完，前面暂存的就没了
        redisUtils.setex(key, currentSize + fileSize, Constants.REDIS_KEY_EXPIRE_ONE_HOUR);
    }

    // 获取临时文件大小
    public Long getFileTempSize(String userId, String fileId) {
        Object sizeObj = redisUtils.get(Constants.REDIS_KEY_USER_FILE_TEMP_SIZE + userId + fileId);
        if (sizeObj == null) {
            return 0L;
        }
        if (sizeObj instanceof Integer) {
            return ((Integer) sizeObj).longValue();
        } else if (sizeObj instanceof Long) {
            return (Long) sizeObj;
        }
        return 0L;
    }

    // 保存下载文件信息
    public void saveDownloadCode(String code, DownloadFileDTO fileDTO) {
        redisUtils.setex(Constants.REDIS_KEY_DOWNLOAD + code, fileDTO, Constants.REDIS_KEY_EXPIRE_FIVE_MIN);
    }

    // 获取下载文件信息
    public DownloadFileDTO getDownloadCode(String code) {
        return (DownloadFileDTO) redisUtils.get(Constants.REDIS_KEY_DOWNLOAD + code);
    }


}
