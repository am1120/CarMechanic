
package model;

import java.sql.Date;
import java.util.Objects;

/**
 *
 * @author Alexander
 */
public class Comment {
    
   /* * * Attributes * * */
   private String commentId;
   private String authorId;
   private String problemId;
   private Date createdAt;
   private String content;

    public Comment() {
    }

    public Comment(String commentId, String authorId, String problemId, Date createdAt, String content) {
        this.commentId = commentId;
        this.authorId = authorId;
        this.problemId = problemId;
        this.createdAt = createdAt;
        this.content = content;
    }

    public String getCommentId() {
        return commentId;
    }

    
    public String getAuthorId() {
        return authorId;
    }

    public String getProblemId() {
        return problemId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public String getContent() {
        return content;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public void setProblemId(String problemId) {
        this.problemId = problemId;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public void setContent(String content) {
        this.content = content;
    }
   
   @Override
    public String toString() {
        return "Comment{" + "commentId=" + commentId + ", authorId=" + authorId + ", problemId=" + problemId + ", createdAt=" + createdAt + ", content=" + content + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.commentId);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Comment other = (Comment) obj;
        if (!Objects.equals(this.commentId, other.commentId)) {
            return false;
        }
        return true;
    }

}
