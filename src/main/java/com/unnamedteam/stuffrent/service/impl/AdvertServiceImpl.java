package com.unnamedteam.stuffrent.service.impl;

import com.unnamedteam.stuffrent.exeptions.AdvertNotFoundException;
import com.unnamedteam.stuffrent.exeptions.URLException;
import com.unnamedteam.stuffrent.model.client.DTO.AdvertDTO;
import com.unnamedteam.stuffrent.model.client.DTO.ResponseAdvert;
import com.unnamedteam.stuffrent.model.client.advert.Advert;
import com.unnamedteam.stuffrent.model.client.advert.AdvertData;
import com.unnamedteam.stuffrent.model.client.user.Users;
import com.unnamedteam.stuffrent.repository.AdvertEntityRepository;
import com.unnamedteam.stuffrent.service.AdvertService;
import com.unnamedteam.stuffrent.service.StorageService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.net.MalformedURLException;
import java.util.List;

@Service
@AllArgsConstructor
public class AdvertServiceImpl implements AdvertService {

    private AdvertEntityRepository advertRepository;
    private StorageService storageService;

    @Override
    public void saveAdvert(AdvertDTO advertDTO, Users owner, MultipartFile file) {
        Advert advert = new Advert();
        advert.setOwnerId(owner.getId());
        owner.setNumberOfAdverts(owner.getNumberOfAdverts() + 1);
        String fileName = storageService.store(file);
        AdvertData data = new AdvertData(advertDTO.getName(),
                advertDTO.getCategory(),
                advertDTO.getPrice(),
                fileName, null, null);
        advert.setAdvertData(data);
        advertRepository.save(advert);
    }

    @Override
    public List<Advert> findAllAdvertsByOwnerId(Long ownerId) {
        return advertRepository.findAllByOwnerId(ownerId);
    }

    @Override
    public ResponseAdvert convertAdvertToResponseAdvert(Advert advert) {
        String filename = advert.getAdvertData().getPhotoName();
        try {
            return new ResponseAdvert(
                    advert.getId(),
                    advert.getAdvertData().getName(),
                    advert.getAdvertData().getCategory(),
                    advert.getAdvertData().getPrice(),
                    advert.isRented(),
                    advert.getRentedById(),
                    advert.getOwnerId(),
                    storageService.load(filename).toUri().toURL(),
                    advert.getAdvertData().getStartRent(),
                    advert.getAdvertData().getEndRent());
        } catch (MalformedURLException e) {
            throw new URLException();
        }

    }

    @Override
    public Advert getAdvertById(Long id) {
        return advertRepository.findAdvertById(id);
    }

    @Override
    public void checkAdvert(Advert advert) {
        if (advert == null) {
            throw new AdvertNotFoundException("Advert with such id not found");
        }
    }

}
