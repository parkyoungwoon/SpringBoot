package com.studySample.studyDescription.post;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class PostController {

    //@RequiredArgsConstructor 사용시 final 을 붙이면 해당 변수를 초기화 해줌
    private final PostService postService;

    // 게시글 작성 페이지
    @GetMapping("/post/write.do")
    public String openPostWrite(@RequestParam(value = "id", required = false) final Long id, Model model) {
        //받는 변수 ID 에 속성 부여, required 는 필수 값 여부
        if(id != null){
            //이부분을 stream 이랑 제내릭으로 바꿀 수 있는지 확인 필요함
            PostResponse post = postService.findPostById(id);
            model.addAttribute("post",post);
        }
        return "post/write";
    }

    // 게시글 저장 페이지
    @PostMapping("/post/save.do")
    public String openPostSave(final PostRequest params) {
        postService.savePost(params);
        return "redirect:/post/list.do";
    }

}
