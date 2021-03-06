package com.example.demo.persistence;

import com.example.demo.model.LegoSet;
import com.example.demo.model.LegoSetDifficulty;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface LegoSetRepository extends MongoRepository<LegoSet, String>, QuerydslPredicateExecutor<LegoSet> {


    Collection<LegoSet> findAllByThemeContaining(String theme);
    Collection<LegoSet> findAllByThemeContaining(String theme, Sort sort);
    Collection<LegoSet> findAllByDifficultyAndNameStartsWith(LegoSetDifficulty difficulty, String name);
    Collection<LegoSet> findAllBy(TextCriteria textCriteria);

    // ?0 - first param ?1 - second param
    @Query("{'delivery.deliveryFee' : {$lt : ?0}}")
    Collection<LegoSet> findAllByDeliveryPriceLessThan(int price);

    // find by review wich with at least a review of 10
    @Query("{'reviews.rating' : {$eq : 10}}")
    Collection<LegoSet> findAllByGreatReviews();

    @Query("{'paymentOptions.id' : ?0}")
    Collection<LegoSet> findByPaymentOptionId(String id);
}
