package com.studySample.studyDescription.post;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostRequest {
    //게시글 요청
    private Long id;
    private String title;
    private String content;
    private String writer;
    private Boolean noticeYn;
}
