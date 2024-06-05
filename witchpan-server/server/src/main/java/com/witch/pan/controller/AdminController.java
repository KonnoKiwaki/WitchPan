package com.witch.pan.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.witch.common.pojo.BizException;
import com.witch.pan.annotation.LoginValidator;
import com.witch.pan.entity.constants.Constants;
import com.witch.pan.entity.dto.SysSettingsDTO;
import com.witch.pan.entity.dto.UserSpaceDTO;
import com.witch.pan.entity.enums.UserStatusEnum;
import com.witch.pan.entity.query.BaseParam;
import com.witch.pan.entity.vo.UserInfoVO;
import com.witch.pan.pojo.UserInfo;
import com.witch.pan.redis.RedisComponent;
import com.witch.pan.service.FileInfoService;
import com.witch.pan.service.UserInfoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author Yuuki
 */
@RestController
@RequestMapping("/admin")
@ResponseBody
public class AdminController {
    @Autowired
    public FileInfoService fileInfoService;

    @Autowired
    public RedisComponent redisComponent;

    @Autowired
    public UserInfoService userInfoService;

    @LoginValidator(checkAdmin = true)
    @RequestMapping("/getSysSettings")
    public SysSettingsDTO getSysSettings() {
        return redisComponent.getSysSettingDto();
    }


    @LoginValidator(checkAdmin = true)
    @RequestMapping("/saveSysSettings")
    public SysSettingsDTO saveSysSettings(@RequestBody SysSettingsDTO sysSettingsDTO) {
        SysSettingsDTO dto = new SysSettingsDTO();
        dto.setRegisterEmailTitle(sysSettingsDTO.getRegisterEmailTitle());
        dto.setRegisterEmailContent(sysSettingsDTO.getRegisterEmailContent());
        dto.setUserInitSpace(sysSettingsDTO.getUserInitSpace());
        redisComponent.saveSysSettingDto(dto);
        return dto;
    }


    @LoginValidator(checkAdmin = true)
    @RequestMapping("/loadUserList")
    public List<UserInfoVO> saveSysSettings(@RequestBody BaseParam baseParam) {
        return userInfoService.loadUserList(baseParam);
    }

    @LoginValidator(checkAdmin = true)
    @RequestMapping("/updateUserStatus")
    public UserInfoVO saveSysSettings(@RequestBody Map<String, String> data) {
        String userId = data.get("userId");
        Integer status = Integer.parseInt(data.get("status"));
        UserInfo userInfo = userInfoService.getOne(new LambdaQueryWrapper<UserInfo>().eq(UserInfo::getId, userId));
        if(status.equals(UserStatusEnum.DISABLE.status())){
            userInfo.setStatus(UserStatusEnum.DISABLE.status());
        } else {
            userInfo.setStatus(UserStatusEnum.ENABLE.status());
        }
        userInfoService.updateById(userInfo);
        UserInfoVO userInfoVO =  new UserInfoVO();
        BeanUtils.copyProperties(userInfo, userInfoVO);
        return userInfoVO;
    }

    @LoginValidator(checkAdmin = true)
    @RequestMapping("/updateUserSpace")
    public void updateUserSpace(@RequestBody Map<String, String> data) {
        String userId = data.get("userId");
        Long space = Long.valueOf(data.get("changeSpace"));
        UserInfo userInfo = userInfoService.getOne(new LambdaQueryWrapper<UserInfo>().eq(UserInfo::getId, userId));
        Long use =  userInfo.getUseSpace();
        if(use > space * Constants.GB) {
            throw new BizException("分配空间小于用户已使用空间，请重新分配");
        }
        userInfo.setTotalSpace(space * Constants.GB);
        userInfoService.updateById(userInfo);
        UserSpaceDTO userSpaceDTO = new UserSpaceDTO();
        userSpaceDTO.setUseSpace(use);
        userSpaceDTO.setTotalSpace(space * Constants.GB);
        redisComponent.saveUserSpaceUse(userId, userSpaceDTO);
    }
}
