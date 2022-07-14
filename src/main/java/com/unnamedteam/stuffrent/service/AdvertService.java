package com.unnamedteam.stuffrent.service;

import com.unnamedteam.stuffrent.model.client.DTO.AdvertDTO;
import com.unnamedteam.stuffrent.model.client.DTO.ResponseAdvert;
import com.unnamedteam.stuffrent.model.client.advert.Advert;
import com.unnamedteam.stuffrent.model.client.user.Users;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface AdvertService {
    void saveAdvert(AdvertDTO advertDTO, Users owner, MultipartFile file);
    List<Advert> findAllAdvertsByOwnerId(Long ownerId);
    List<Advert> findAll();
    ResponseAdvert convertAdvertToResponseAdvert(Advert advert);
    Advert getAdvertById(Long id);
    void checkAdvertOnExistence(Advert advert);
    void checkAdvertOnRent(Advert advert);
}
