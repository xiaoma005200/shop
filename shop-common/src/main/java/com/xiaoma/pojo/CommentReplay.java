package com.xiaoma.pojo;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
    * 产品评价回复表
    */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentReplay {
    private Long id;

    private Long commentId;

    private String memberNickName;

    private String memberIcon;

    private String content;

    private Date createTime;

    /**
    * 评论人员类型；0->会员；1->管理员
    */
    private Integer type;
}