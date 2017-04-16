/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.dao.product;

import com.avn.ccl.model.product.Product;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Hirantha
 */
public interface ProductDAO {

    public List<Product> getProductList() throws Exception;

    public Map<String, String> getProductDropdownList() throws Exception;

    public Product getProductById(String id) throws Exception;

    public Map<String, String> getProductDropdownListPipeline(String createUser) throws Exception;
}
