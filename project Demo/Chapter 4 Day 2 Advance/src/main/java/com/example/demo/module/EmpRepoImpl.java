package com.example.demo.module;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.demo.entities.Address;
import com.example.demo.entities.Employee;
import com.example.demo.helper.HelperExtension;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.Subquery;

@Component
public class EmpRepoImpl implements EmpRepo {

	//step-1
	@PersistenceContext 
	private EntityManager em;

	HelperExtension helper=new HelperExtension();

	
	//inner join by default in criteria
	
	@Override
	public List<Employee> getData(int age,String status, String search, String searchBy, String sort, String sortBy, int itemPerPage, int pageNumber) {
		
		//step-2
		CriteriaBuilder cb = em.getCriteriaBuilder();

		//step-3
		CriteriaQuery<Employee> cq = cb.createQuery(Employee.class);
		Root<Employee> root = cq.from(Employee.class);
		
		CriteriaQuery<Employee> criteriaQuery = cq.select(root);
		
		List<Predicate> condtionsList = new ArrayList<Predicate>();
		//condition-1
		
		
		if(age>0) {
		 condtionsList.add(cb.equal(root.get("age"), age));
		}
	
		if(!helper.isNullOrEmpty(status)) {
			  condtionsList.add(cb.like(root.get("status"), status));
			}

		//condition-5-search
		if(!helper.isNullOrEmpty(searchBy) && !helper.isNullOrEmpty(search)) {
		  condtionsList.add(cb.like(root.get(searchBy).as(String.class), "%" + search + "%"));
		}
		
				
		//condition -6-sort
		if(!helper.isNullOrEmpty(sort) && !helper.isNullOrEmpty(sortBy)) {
			if(sort!=null && sort.equalsIgnoreCase("asc")) {
			    cq.orderBy(cb.asc(root.get(sortBy)));
			}else {
				cq.orderBy(cb.desc(root.get(sortBy)));
			}
	   }
		
		
		cq.where(cb.and(condtionsList.toArray(new Predicate[condtionsList.size()])));
		
		if(itemPerPage>0 && pageNumber>0) {
			final TypedQuery<Employee> typedQuery = em.createQuery(cq);
			typedQuery.setFirstResult((pageNumber - 1) * itemPerPage);
			typedQuery.setMaxResults(itemPerPage);
			return typedQuery.getResultList();
		}
				
		
		//step-5
		TypedQuery<Employee> typedQuery = em.createQuery(criteriaQuery);
		List<Employee> allitems = typedQuery.getResultList();

		return allitems;
	}




	
	
	@Override
	public long getCount(int age,String status, String search, String searchBy, String sort, String sortBy, int itemPerPage, int pageNumber) {
		//step-2
				CriteriaBuilder cb = em.getCriteriaBuilder();

				//step-3
				//change-1st
				CriteriaQuery<Long> cq = cb.createQuery(Long.class);
				Root<Employee> root = cq.from(Employee.class);
				
				//change-2nd
				cq.select(cb.count(root));

				
				//step-4 fir condition.
				
				List<Predicate> condtionsList = new ArrayList<Predicate>();
				//condition-1
				
				
				if(age>0) {
				 condtionsList.add(cb.equal(root.get("age"), age));
				}
				
				if(!helper.isNullOrEmpty(status)) {
					  condtionsList.add(cb.like(root.get("status"), status));
					}

				//condition-5-search
				if(!helper.isNullOrEmpty(searchBy) && !helper.isNullOrEmpty(search)) {
				  condtionsList.add(cb.like(root.get(searchBy).as(String.class), "%" + search + "%"));
				}
				
						
				//condition -6-sort
				if(!helper.isNullOrEmpty(sort) && !helper.isNullOrEmpty(sortBy)) {
					if(sort!=null && sort.equalsIgnoreCase("asc")) {
					    cq.orderBy(cb.asc(root.get(sortBy)));
					}else {
						cq.orderBy(cb.desc(root.get(sortBy)));
					}
			   }
				

				cq.where(cb.and(condtionsList.toArray(new Predicate[condtionsList.size()])));
				
				//change-3rd				
				return em.createQuery(cq).getSingleResult();

	}
	

}
