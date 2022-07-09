package com.unnamedteam.stuffrent.service;

import com.unnamedteam.stuffrent.model.client.DTO.AdvertDTO;
import com.unnamedteam.stuffrent.model.client.DTO.ResponseAdvert;
import com.unnamedteam.stuffrent.model.client.advert.Advert;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface AdvertService {
    void saveAdvert(AdvertDTO advertDTO, Long ownerId, MultipartFile file);
    List<Advert> findAllAdvertsByOwnerId(Long ownerId);
    ResponseAdvert convertAdvertToResponseAdvert(Advert advert);
}
