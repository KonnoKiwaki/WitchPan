package com.witch.pan.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.witch.common.annotation.ResponseResult;
import com.witch.pan.annotation.LoginValidator;
import com.witch.pan.entity.constants.Constants;
import com.witch.pan.entity.dto.ShareDTO;
import com.witch.pan.entity.vo.FileShareVo;
import com.witch.pan.entity.vo.SessionWebUserVO;
import com.witch.pan.pojo.FileShare;
import com.witch.pan.service.FileShareService;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotEmpty;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Yuuki
 * @since 2023-07-14
 */
@RestController
@RequestMapping("/share")
@ResponseResult
@LoginValidator
@Validated
public class ShareController {

    private final FileShareService fileShareService;

    public ShareController(FileShareService fileShareService) {
        this.fileShareService = fileShareService;
    }

    // 根据category，加载所有数据
    @GetMapping("/loadShareList")
    public IPage<FileShareVo> loadShareList(HttpSession session,
                                            @RequestParam(required = false, defaultValue = "1") Integer page,
                                            @RequestParam(required = false, defaultValue = "10") Integer limit) {

        Page<FileShare> pageParam = new Page<>(page, limit);
        String userId = ((SessionWebUserVO) session.getAttribute(Constants.SESSION_KEY)).getId();
        return fileShareService.pageInfo(pageParam, userId);
    }

    @PostMapping("/shareFile")
    public FileShare shareFile(HttpSession session, @RequestBody ShareDTO shareDTO) {
        String userId = ((SessionWebUserVO) session.getAttribute(Constants.SESSION_KEY)).getId();
        FileShare fileShare = new FileShare();
        fileShare.setUserId(userId);
        BeanUtils.copyProperties(shareDTO, fileShare);
        return fileShareService.saveShare(fileShare);
    }

    @DeleteMapping("/cancelShare/{shareIds}")
    public void cancelShare(HttpSession session, @PathVariable("shareIds") @NotEmpty String shareIds) {
        String userId = ((SessionWebUserVO) session.getAttribute(Constants.SESSION_KEY)).getId();
        fileShareService.deleteShareBatch(shareIds, userId);
    }
}

