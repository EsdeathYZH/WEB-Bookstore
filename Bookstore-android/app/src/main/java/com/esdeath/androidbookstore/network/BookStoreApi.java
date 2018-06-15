package com.esdeath.androidbookstore.network;

import com.esdeath.androidbookstore.model.Book;
import com.esdeath.androidbookstore.model.BooklistResponse;


import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;


public interface BookStoreApi {

    @GET("book/books")
    Observable<List<Book>> getBooks();

}
