package com.store.interceptor;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.store.entity.Category;
import com.store.entity.PByCID;
import com.store.entity.Product;
import com.store.service.CategoryService;
import com.store.service.ProductService;

@Component
public class GlobalInterceptor implements HandlerInterceptor {
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	ProductService productService;

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		List<Category> list = categoryService.findAll();
		List<PByCID> pBycId = new ArrayList<>();
		for (Category category : list) {
			List<Product> p = productService.findByCategoryId(category.getId());
			pBycId.add(new PByCID(category.getId(), category.getName(), p.size()));
		}
		request.setAttribute("categories", list);
		request.setAttribute("pBycId", pBycId);
	}

}
