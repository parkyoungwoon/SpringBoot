package com.studySample.studyDescription.post;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper //스프링 부트 특성으로 따로 context 설정 없이 mybatis id 에 맞춰 주면 설정 가능
public interface PostMapper {
    /**
    게시글 저장
    @param params - 게시글 정보
     */
    void save(PostRequest params);

    /**
    게시글 상세정보 조회
    @param id - PK
    @return 게시글상세정보
     */
    PostResponse findById(Long id);

    /**
    게시글 수정
    @param params - 게시글 정보
     */
    void update(PostRequest params);

    /**
    게시글 삭제
    @param id - PK
     */
    void deleteById(Long id);

    /**
    게시글 리스트 조회
    @return 게시글 리스트
     */
    List<PostResponse> findAll();



}
