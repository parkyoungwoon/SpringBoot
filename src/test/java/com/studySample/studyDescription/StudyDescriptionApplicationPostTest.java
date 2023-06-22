package com.studySample.studyDescription;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.studySample.studyDescription.post.PostMapper;
import com.studySample.studyDescription.post.PostRequest;
import com.studySample.studyDescription.post.PostResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class StudyDescriptionApplicationPostTest {

    @Autowired
    PostMapper postMapper;

    @Test
    void saveTest() {
        PostRequest params = new PostRequest();
        params.setTitle("1번 게시글 제목");
        params.setContent("1번 게시글 내용");
        params.setWriter("테스터");
        params.setNoticeYn(false);
        postMapper.save(params);

        List<PostResponse> posts = postMapper.findAll();
        System.out.println("전체 게시글 개수는 : " + posts.size() + "개입니다.");
    }

    @Test
    void findKeyTest() {
        PostRequest params = new PostRequest();
        //PostResponse posts = postMapper.findById(Integer.toUnsignedLong(1));
        PostResponse posts = postMapper.findById(1L);
        System.out.println("전체 게시글 아이디 1번은 : " + posts.getContent() + "입니다.");
        String postJson = "";
        try{
            postJson = new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(posts);
        }catch(Exception e){
            System.out.println("에러로그 출력 : "+e);
        }
        System.out.println("전체 게시글 아이디 1번 전체 내용은 : " + postJson + "입니다.");
    }

    @Test
    void updateTest() {
        PostRequest params = new PostRequest();
        params.setId(1L);
        params.setTitle("1번 게시글 제목2");
        params.setContent("1번 게시글 내용2");
        params.setWriter("테스터2");
        params.setNoticeYn(false);
        postMapper.update(params);

        List<PostResponse> posts = postMapper.findAll();
        System.out.println("전체 게시글 개수는 : " + posts.size() + "개입니다.");
        String postJson = "";
        try{
            postJson = new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(posts);
        }catch(Exception e){
            System.out.println("에러로그 출력 : "+e);
        }
        System.out.println("전체 게시글 아이디 1번 전체 내용은 : " + postJson + "입니다.");
    }

    @Test
    void deleteTest() {
        PostRequest params = new PostRequest();
        postMapper.deleteById(1L);

        List<PostResponse> posts = postMapper.findAll();
        System.out.println("전체 게시글 개수는 : " + posts.size() + "개입니다.");

    }
}
