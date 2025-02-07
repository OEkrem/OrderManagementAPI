package com.oekrem.SpringMVCBackEnd.config;

import com.oekrem.SpringMVCBackEnd.Dto.Response.OrderDetailResponse;
import com.oekrem.SpringMVCBackEnd.Dto.Response.OrderResponse;
import com.oekrem.SpringMVCBackEnd.Models.Order;
import com.oekrem.SpringMVCBackEnd.Models.OrderDetail;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration
public class ModelMapperConfig {

    @Bean
    @Qualifier("defaultModelMapper")
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        // İç içe nesne eşlemeyi aktif hale getirme
        modelMapper.getConfiguration().setFieldMatchingEnabled(true)
                .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE);

        return new ModelMapper();
    }

    @Bean
    @Qualifier("orderDetailModelMapper")
    public ModelMapper orderDetailModelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.typeMap(OrderDetail.class, OrderDetailResponse.class).addMappings(mapper -> {
            mapper.map(src -> src.getProduct().getId(), OrderDetailResponse::setProductId);
            mapper.map(src -> src.getOrder().getId(), OrderDetailResponse::setOrderId);
        });
        return modelMapper;
    }

    @Bean
    @Qualifier("orderModelMapper")
    public ModelMapper orderModelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.typeMap(Order.class, OrderResponse.class).addMappings(mapper -> {
            mapper.map(src -> src.getUser().getId(), OrderResponse::setUserId);
        });
        return modelMapper;
    }

}
