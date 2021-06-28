package by.boginsky.services;

import by.boginsky.domain.Tag;


public interface ITagService {

    Tag findOrCreate(Tag tag);
}

