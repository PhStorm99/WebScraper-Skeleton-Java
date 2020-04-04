package scraper;

import java.util.Date;
import net.dean.jraw.models.Submission;

/**
 * wrapper class for Submission in jraw.
 * only to be created by scrapper class but used by any scope.
 *
 * @author Shariar
 */
public class Post {
    
    private Submission submission;
    
    Post( Submission post){
        this.submission = post;
    }
    
    public boolean isImage(){
        return "image".equalsIgnoreCase(submission.getPostHint());
    }
    
    public String getPostHint(){
        return submission.getPostHint();
    }
    
    public String getTitle(){
        return submission.getTitle();
    }
    
    public String getUrl(){
        return submission.getUrl();
    }
    
    public boolean isOver18(){
        return submission.isNsfw();
    }
    
    public Date getDate(){
        return submission.getCreated();
    }
    
    public int getVoteCount(){
        return submission.getScore();
    }
}
