package com.target.targetcasestudy.core.network.model;

import android.os.Parcelable;

import androidx.annotation.Nullable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

import java.util.List;

@AutoValue
public abstract class Deals implements Parcelable {

    @AutoValue
    public static abstract class Products implements Parcelable {

        public static TypeAdapter<Products> typeAdapter(Gson gson) {
            return new $AutoValue_Deals_Products.GsonTypeAdapter(gson);
        }

        @SerializedName("products")
        public abstract List<Product> products();

    }

    @AutoValue
    public static abstract class Product implements Parcelable {

        public static TypeAdapter<Product> typeAdapter(Gson gson) {
            return new $AutoValue_Deals_Product.GsonTypeAdapter(gson);
        }

        @SerializedName("id")
        public abstract Integer id();

        @SerializedName("title")
        public abstract String title();

        @SerializedName("aisle")
        public abstract String aisle();

        @SerializedName("description")
        public abstract String description();

        @SerializedName("image_url")
        public abstract String imageUrl();

        @SerializedName("regular_price")
        public abstract RegularPrice regularPrice();

        @Nullable
        @SerializedName("sale_price")
        public abstract SalePrice salePrice();

    }

    @AutoValue
    public static abstract class RegularPrice implements Parcelable {

        public static TypeAdapter<RegularPrice> typeAdapter(Gson gson) {
            return new $AutoValue_Deals_RegularPrice.GsonTypeAdapter(gson);
        }

        @SerializedName("amount_in_cents")
        public abstract Integer amountInCents();

        @SerializedName("currency_symbol")
        public abstract String currencySymbol();

        @SerializedName("display_string")
        public abstract String displayString();

    }

    @AutoValue
    public static abstract class SalePrice implements Parcelable {

        public static TypeAdapter<SalePrice> typeAdapter(Gson gson) {
            return new $AutoValue_Deals_SalePrice.GsonTypeAdapter(gson);
        }

        @SerializedName("amount_in_cents")
        public abstract String amountInCents();

        @SerializedName("currency_symbol")
        public abstract String currencySymbol();

        @SerializedName("display_string")
        public abstract String displayString();

    }

}
