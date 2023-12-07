package com.example.uzum_market.service;

import com.example.uzum_market.dto.*;
import com.example.uzum_market.exceptions.RestException;
import com.example.uzum_market.model.*;
import com.example.uzum_market.repository.*;
import com.example.uzum_market.utils.CommonUtils;
import com.example.uzum_market.utils.MakeQuery2;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final PriceRepository priceRepository;
    private final ProductRepository productRepository;
    private final RatingRepository ratingRepository;
    private final CommentRepository commentRepository;
    private final CategoryRepository categoryRepository;
    private final SellerRepository sellerRepository;
    private final ColorRepository colorRepository;
    private final AttachmentRepository attachmentRepository;
    private final SpecificationRepository specificationRepository;
    private final HistoryItemRepository historyItemRepository;

    @Override
    public ApiResult<PaginationDTO<ProductForCategoryDTO>> getProductsList(MainCriteriaDTO mainCriteriaDTO, Integer categoryId) {

        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new RuntimeException("Category not found"));
        String query = MakeQuery2.makeQueryForProduct(mainCriteriaDTO, categoryId, "user");

        List<Integer> allByMyQuery = productRepository.findAllByMyQuery(query);
        List<Product> products = productRepository.findAllById(allByMyQuery);
        Integer countOfProduct = productRepository.countAllByCategoryId(categoryId);

        PageSize result = getPageSize(mainCriteriaDTO.getSize(), countOfProduct);

        List<ProductDTO> productDTOS = mapToProductDTOS(products);
        ProductForCategoryDTO productForCategoryDTO = ProductForCategoryDTO.builder()
                .productCount(result.countOfProduct())
                .categoryName(category.getNameUz())
                .productDTOS(productDTOS)
                .build();
        PaginationDTO<ProductForCategoryDTO> paginationDTO =
                PaginationDTO.makePagination(List.of(productForCategoryDTO), result.totalPage(), result.countOfProduct());

        return ApiResult.successResponse(paginationDTO);
    }

    private PageSize getPageSize(Integer size, Integer countOfProduct) {
        int pageCon = -1;
        if (size != 0) pageCon = countOfProduct / size;
        if (size <= 0) size = 10;
        int totalPage = countOfProduct % size > 0 ? (pageCon + 1) : pageCon;
        return new PageSize(countOfProduct, totalPage);
    }


    private List<ProductDTO> mapToProductDTOS(List<Product> products) {

        List<ProductDTO> productDTOS = new LinkedList<>();
        for (Product product : products) {
            Price price = priceRepository.findFirstByProductIdOrderByPrice(product.getId()).orElseThrow(() -> new RuntimeException("Price not found"));
            Double price1 = price.getPrice();

            double discountPrice = price1 - (price1 * product.getDiscount()) / 100;
            RatingDTO ratingDTO = ratingRepository.findAverageAndTotalCountByProductId(product.getId());

            ProductDTO productDTO = ProductDTO.builder()
                    .ratingDTO(ratingDTO)
                    .attachments(price.getAttachments())
                    .productId(product.getId())
                    .price(price.getPrice())
                    .curPrice(discountPrice)
                    .name(product.getNameUz())
                    .build();
            productDTOS.add(productDTO);
        }
        return productDTOS;
    }

    @Override
    public ApiResult<PaginationDTO<ProductDTO>> getProductsListForAdmin(MainCriteriaDTO mainCriteriaDTO, Integer sellerId) {
        String query = MakeQuery2.makeQueryForProduct(mainCriteriaDTO, sellerId, "admin");
        List<Integer> allByMyQuery = productRepository.findAllByMyQuery(query);
        Integer countOfProduct = productRepository.countAllBySellerId(sellerId);
        List<Product> products = productRepository.findAllById(allByMyQuery);
        List<ProductDTO> productDTOS = mapToProductDTOS(products);
        PageSize pageSize = getPageSize(mainCriteriaDTO.getSize(), countOfProduct);

        PaginationDTO.builder().content(Collections.singletonList(productDTOS)).build();
        PaginationDTO<ProductDTO> productDTOPaginationDTO = PaginationDTO.makePagination(productDTOS, pageSize.totalPage(), pageSize.countOfProduct());
        return ApiResult.successResponse(productDTOPaginationDTO);
    }

    @Override
    @Transactional
    public ApiResult<ProductOneDTO> getOne(Integer productId) {

        Product product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product not found"));
        List<Price> prices = priceRepository.getAllByProductIdOrderByPrice(productId);
        RatingDTO ratingDTO = ratingRepository.findAverageAndTotalCountByProductId(product.getId());
        Price price = prices.get(0);
        Double priceAmount = price.getPrice();
        double discountPrice = priceAmount - (priceAmount * product.getDiscount()) / 100;
        List<Color> colors = new LinkedList<>();
        List<Specification> specifications = new LinkedList<>();
        List<Attachment> attachments = new LinkedList<>();
        for (Price price1 : prices) {
            colors.add(price1.getColor());
            specifications.add(price1.getSpecification());
            attachments.addAll(price1.getAttachments());
        }
        PriceDTO priceDTO = mapToPriceDTO(price, discountPrice);

        List<Comment> comments = commentRepository.getAllByProductId(productId);

        SellerDTO sellerDTO = SellerDTO.builder()
                .attachment(product.getSeller().getUser().getAttachment())
                .name(product.getSeller().getUser().getName())
                .id(product.getSeller().getId())
                .build();
        ProductOneDTO productOneDTO = ProductOneDTO.builder()
                .id(productId)
                .attachments(attachments)
                .comments(comments)
                .ratingDTO(ratingDTO)
                .colors(colors)
                .description(product.getDescription())
                .sellerDTO(sellerDTO)
                .specifications(specifications)
                .price(priceDTO)
                .build();


        return ApiResult.successResponse(productOneDTO);
    }

    @Override
    @Transactional
    public Integer add(ProductAddDTO productAddDTO) {
        Integer sellerId = CommonUtils.getCurrentUserFromContext().getId();
        Product productAdd = Product.builder()
                .brand(productAddDTO.brand())
                .description(productAddDTO.description())
                .category(categoryRepository.findById(productAddDTO.categoryId()).orElseThrow(() -> new RuntimeException("Category not found")))
                .seller(sellerRepository.findById(sellerId).orElseThrow(() -> new RuntimeException(" ")))
                .isStock(productAddDTO.isStock())
                .nameUz(productAddDTO.name())
                .nameRu(productAddDTO.name())
                .discount(productAddDTO.discount())
                .isActive(productAddDTO.isActive())
                .build();

        Product product = productRepository.save(productAdd);


        List<PriceAddDTO> prices = productAddDTO.priceAddDTOS();
        for (PriceAddDTO price : prices) {
            List<Attachment> attachments = attachmentRepository.findAllById(productAddDTO.photoList());

            Color color = colorRepository.save(Color.builder().name(price.color().name()).build());
            Specification specification = specificationRepository.save(Specification.builder().name(productAddDTO.specificationType()).title(price.specificationName()).build());
            Price price1 = Price.builder()
                    .price(price.price())
                    .color(color)
                    .attachments(attachments)
                    .count(price.count())
                    .product(product)
                    .specification(specification)
                    .build();
            priceRepository.save(price1);
        }
        return product.getId();

    }

    @Override
    public Integer edit(ProductAddDTO productAddDTO, Integer id) {


        return null;
    }

    @Override
    public ApiResult<Integer> delete(Integer id) {
        if (!productRepository.existsById(id)) {
            throw RestException.restThrow("Product Not found", HttpStatus.NOT_FOUND);
        }
        if (historyItemRepository.existsHistoryItemByProductId(id)) {
            throw RestException.restThrow("could not delete, it is used", HttpStatus.NOT_MODIFIED);
        }

        productRepository.deleteById(id);
        return ApiResult.successResponse(id);
    }

    @Override
    public void changeStatus(Integer id) {
        Product product = productRepository.findById(id).orElseThrow(() -> RestException.restThrow("Product not found",HttpStatus.NOT_FOUND));
        product.setIsActive(!product.getIsActive());
    }

    private static PriceDTO mapToPriceDTO(Price price, double discountPrice) {
        return PriceDTO.builder()
                .count(price.getCount())
                .price(price.getPrice())
                .curPrice(discountPrice)
                .id(price.getId())
                .build();

    }
}
