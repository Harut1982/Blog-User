package storage.impl;

import exception.PostNotFoundException;
import model.Post;
import storage.PostStorage;

public class PostStorageImpl implements PostStorage {
    private Post[] posts = new Post[16];
    private int size = 0;

    @Override
    public void add(Post post) {
        if (posts.length == size) {
            extend();

        }
        posts[size++] = post;

    }

    private void extend() {
        Post[] tmp = new Post[posts.length + 10];
        System.arraycopy(posts, 0, tmp, 0, posts.length);
        posts = tmp;
    }

    @Override
    public Post getPostByTitle(String title) throws PostNotFoundException {
        for (int i = 0; i < size; i++) {
            if (posts[i].getTitle().equals(title)) {
                return posts[i];
            }
        }
        throw new PostNotFoundException("Post not found!");
    }

    @Override
    public void searchPostsByKeyword(String keyword) {
        for (int i = 0; i < size; i++) {
            if (posts[i].getTitle().contains(keyword) || posts[i].getText().contains(keyword)) {
                System.out.println(posts[i]);
            }

        }

    }

    @Override
    public void printPostsByCategory(String category) {
        for (int i = 0; i < size; i++) {
            if (category.equals(posts[i].getCategory())) {
                System.out.println(posts[i]);
            }
        }

    }

    @Override
    public void printAllPosts() {
        System.out.println("-----------------------------------");
        for (int i = 0; i < size; i++) {
            System.out.println(posts[i]);

        }
        System.out.println("----------------------------------");

    }
}
