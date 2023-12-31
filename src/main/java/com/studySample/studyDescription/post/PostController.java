package com.studySample.studyDescription.post;

import com.studySample.studyDescription.common.MessageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

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
    /*@PostMapping("/post/save.do")
    public String openPostSave(final PostRequest params) {
        postService.savePost(params);
        return "redirect:/post/list.do";
    }*/
    // 신규 게시글 생성
    @PostMapping("/post/save.do")
    public String savePost(final PostRequest params, Model model) {
        postService.savePost(params);
        MessageDto message = new MessageDto("게시글 생성이 완료되었습니다.", "/post/list.do", RequestMethod.GET, null);
        return showMessageAndRedirect(message, model);
    }

    //게시글 조회 페이지
    @GetMapping("/post/list.do")
    public String openPostList(Model model){
        List<PostResponse> posts = postService.findAllPost();
        model.addAttribute("posts",posts);
        return "post/list";
    }

    //게시물 상세 페이지
    @GetMapping("/post/view.do")
    public String openPostView(@RequestParam final Long id, Model model) {
        //주소가 "/post/view.do?id=1" 이런식으로 들어오는 방식을 쿼드 스트링이라고함
        if(id != null){
            //이부분을 stream 이랑 제내릭으로 바꿀 수 있는지 확인 필요함
            PostResponse post = postService.findPostById(id);
            model.addAttribute("post",post);
        }
        return "post/view";
    }

    // 게시글 수정 메세지
    /*@PostMapping("/post/update.do")
    public String openPostUpdate(final PostRequest params) {
        postService.updatePost(params);
        return "redirect:/post/list.do";
    }*/
    @PostMapping("/post/update.do")
    public String updatePost(final PostRequest params, Model model) {
        postService.updatePost(params);
        MessageDto message = new MessageDto("게시글 수정이 완료되었습니다.", "/post/list.do", RequestMethod.GET, null);
        return showMessageAndRedirect(message, model);
    }

    // 게시글 삭제
    /*@PostMapping("/post/delete.do")
    public String deletePost(@RequestParam final Long id) {
        postService.deletePost(id);
        return "redirect:/post/list.do";
    }*/
    @PostMapping("/post/delete.do")
    public String deletePost(@RequestParam final Long id, Model model) {
        postService.deletePost(id);
        MessageDto message = new MessageDto("게시글 삭제가 완료되었습니다.", "/post/list.do", RequestMethod.GET, null);
        return showMessageAndRedirect(message, model);
    }

    // 사용자에게 메시지를 전달하고, 페이지를 리다이렉트 한다.
    private String showMessageAndRedirect(final MessageDto params, Model model) {
        model.addAttribute("params", params);

        return "common/messageRedirect";
    }


}
