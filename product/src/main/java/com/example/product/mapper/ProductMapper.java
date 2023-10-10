package com.example.product.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.example.product.entities.Product;
import com.example.product.request.ProductPostRequestBody;
import com.example.product.request.ProductPutRequestBody;

@Mapper(componentModel = "spring")
public abstract class ProductMapper {
	
	public static final ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);
	
	public abstract Product toProduct(ProductPostRequestBody productPostRequestBody);
	
	public abstract Product toProduct(ProductPutRequestBody productPutRequestBody);

}
