package onespot.pivotal.api.dao;

import com.google.common.collect.Multimap;
import com.google.gson.reflect.TypeToken;
import onespot.pivotal.api.resources.Comment;
import onespot.pivotal.rest.JsonRestClient;
import onespot.pivotal.rest.PivotalRestClient;

import java.util.List;

/**
 * Created by ian on 3/30/15.
 */
public class CommentsDAO extends DAO {
    public CommentsDAO(JsonRestClient jsonRestClient, String pathPrefix, Multimap<String, String> params) {
        super(jsonRestClient, pathPrefix, params);
    }

    public List<Comment> get() {

        List<Comment> comments = jsonRestClient.get(new TypeToken<List<Comment>>() {
        }.getType(), path, params);
        for (Comment comment : comments) {
            comment.setUrl(PivotalRestClient.URL_UI + this.path + comment.getId());
        }
        return comments;
    }

    public Comment get(int id) {
        Comment comment = jsonRestClient.get(Comment.class, path + "/" + id, params);
        comment.setUrl(PivotalRestClient.URL_UI + path + "/" + id);
        return comment;
    }

    public void put(int id, Comment comment) {
        jsonRestClient.put(Comment.class, path + "/" + id, params, comment);
    }

    public void post(Comment comment) {
        jsonRestClient.post(Comment.class, path, params, comment);
    }
}
