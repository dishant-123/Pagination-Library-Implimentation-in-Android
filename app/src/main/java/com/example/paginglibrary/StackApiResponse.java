package com.example.paginglibrary;

import java.util.List;

class Owner{
    public int reputation;
    public int user_id;
    public String user_type;
    public int accept_rate;
    public String profile_image;
    public String display_name;
    public String link;
}
class Item{
    public Owner owner;
    public Boolean is_accepted;
    public int score;
    public long last_activity_date;
    public long creation_date;
    public long answer_id;
    public long question_id;
    public String content_license;

}
public class StackApiResponse {
    public List<Item> items;
    public Boolean has_more;
    public int backoff;
    public int quota_max;
    public int quota_remaining;
}
