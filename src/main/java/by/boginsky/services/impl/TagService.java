package by.boginsky.services.impl;

import by.boginsky.domain.Tag;
import by.boginsky.repositories.TagRepository;
import by.boginsky.services.ITagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TagService implements ITagService {

    private final TagRepository tagRepository;

    @Autowired
    TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @Override
    @Transactional
    public Tag findOrCreate(Tag tag) {
        List<Tag> foundTags = tagRepository.findByName(tag.getName());
        if (foundTags.isEmpty()) {
            tagRepository.save(tag);
            return tag;
        } else {
            return foundTags.get(0);
        }
    }
}