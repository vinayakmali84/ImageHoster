


package ImageHoster.controller;

import ImageHoster.model.Comment;
import ImageHoster.model.Image;
import ImageHoster.model.User;
import ImageHoster.service.CommentService;
import ImageHoster.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private ImageService imageService;

    //This method creates new comment for image
    @RequestMapping(value = "/image/{imageId}/{imageTitle}/comments", method = RequestMethod.POST)
    public String createComment(@PathVariable("imageId") Integer imageId, @PathVariable("imageTitle") String imageTitle, @RequestParam("comment") String text,Comment comment, HttpSession session) throws IOException {
        User user = (User) session.getAttribute("loggeduser");
        Image image = imageService.getImage(imageId);
        comment.setUser(user);
        comment.setText(text);
        comment.setImage(image);
        comment.setCreatedDate(new Date());
        commentService.addComment(comment);
        return "redirect:/images/" + imageId + "/new";
    }

/*
    @RequestMapping(value = "/image/{id}/{title}/comments", method = RequestMethod.POST)
    public String createComment(@PathVariable("id") Integer imageId, @PathVariable("title") String imageTitle, @RequestParam("comment") String comment, HttpSession session) throws IOException {

        Image newImage = imageService.getImage(imageId);
        User user = (User) session.getAttribute("loggeduser");

        Comment comment1 = new Comment();
        comment1.setText(comment);
        comment1.setUser(user);
        comment1.setImage(newImage);
        comment1.setCreatedDate(new Date());
        commentService.addComment(comment1);

        return "redirect:/images";
    }

    */
}


