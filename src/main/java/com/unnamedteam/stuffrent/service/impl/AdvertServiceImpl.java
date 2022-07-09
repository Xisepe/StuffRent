package com.unnamedteam.stuffrent.service.impl;

import com.unnamedteam.stuffrent.model.client.DTO.AdvertDTO;
import com.unnamedteam.stuffrent.model.client.DTO.ResponseAdvert;
import com.unnamedteam.stuffrent.model.client.advert.Advert;
import com.unnamedteam.stuffrent.model.client.advert.AdvertData;
import com.unnamedteam.stuffrent.repository.AdvertEntityRepository;
import com.unnamedteam.stuffrent.service.AdvertService;
import com.unnamedteam.stuffrent.service.StorageService;
import lombok.AllArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.util.List;

@Service
@AllArgsConstructor
public class AdvertServiceImpl implements AdvertService {

    private AdvertEntityRepository advertRepository;
    private StorageService storageService;

    @Override
    public void saveAdvert(AdvertDTO advertDTO, Long ownerId, MultipartFile file) {
        Advert advert = new Advert();
        advert.setOwnerId(ownerId);
        String fileName = storageService.store(file);
        AdvertData data = new AdvertData(advertDTO.getName(),
                advertDTO.getCategory(),
                advertDTO.getPrice(),
                fileName);
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
            Resource resource = storageService.loadAsResource(filename);
            return new ResponseAdvert(advert.getAdvertData().getName(),
                    advert.getAdvertData().getCategory(),
                    advert.getAdvertData().getPrice(),
                    advert.isRented(),
                    advert.getRentedById(),
                    resource);
        } catch (FileNotFoundException e) {
            throw new com.unnamedteam.stuffrent.exeptions.FileNotFoundException("Could not find file");
        }
    }
}
