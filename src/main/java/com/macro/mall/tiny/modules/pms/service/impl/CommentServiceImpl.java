package com.macro.mall.tiny.modules.pms.service.impl;

import com.macro.mall.tiny.modules.pms.model.Comment;
import com.macro.mall.tiny.modules.pms.mapper.CommentMapper;
import com.macro.mall.tiny.modules.pms.service.CommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商品评价表 服务实现类
 * </p>
 *
 * @author XuShu
 * @since 2021-09-18
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

}
