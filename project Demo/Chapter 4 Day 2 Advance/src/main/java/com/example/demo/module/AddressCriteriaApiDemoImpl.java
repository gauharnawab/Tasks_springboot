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
public class AddressCriteriaApiDemoImpl implements AddressCriteriaApiDemo {

	//step-1
	@PersistenceContext 
	private EntityManager em;

	HelperExtension helper=new HelperExtension();
	
	//https://www.baeldung.com/jpa-criteria-api-in-expressions
	
	//sub query in criteria
	
//	@Override
//	public List<Address> getEmployeeAddressDataBySubQuery() {
//		
//		//step-2
//		CriteriaBuilder cb = em.getCriteriaBuilder();
//
//		//step-3
//		CriteriaQuery<Address> cq = cb.createQuery(Address.class);
//		Root<Address> address = cq.from(Address.class);
//		
//		//subquery start
//		Subquery<Employee> subquery = cq.subquery(Employee.class);
//		Root<Employee> emp = subquery.from(Employee.class);
//		subquery.select(emp)
//		  .distinct(true)
//		  .where(cb.like(emp.get("name"), "%" + "xyz" + "%"));
//		
//		cq.select(address).where(cb.in(address.get("employee")).value(subquery));
//		//subquery end
//		
//		
//		//step-5
//		TypedQuery<Address> typedQuery = em.createQuery(cq);
//		List<Address> allitems = typedQuery.getResultList();
//
//		return allitems;
//	}
	
	

	
	
	
	
	
	
	//inner join by default in criteria
	
	@Override
	public List<Address> getData(String city, String state, int age, String search, String searchBy, String sort, String sortBy, int itemPerPage, int pageNumber) {
		
		//step-2
		CriteriaBuilder cb = em.getCriteriaBuilder();

		//step-3
		CriteriaQuery<Address> cq = cb.createQuery(Address.class);
		Root<Address> root = cq.from(Address.class);
		
		//step-4 fir condition.
		CriteriaQuery<Address> criteriaQuery = cq.select(root);
		
		List<Predicate> condtionsList = new ArrayList<Predicate>();
		//condition-1
		
		if(!helper.isNullOrEmpty(city)) {
		 condtionsList.add(cb.equal(root.get("city") ,city));
		}
		//condition-2
		
		if(age>0) {
		 condtionsList.add(cb.equal(root.get("employee").get("age"), age));
		}
		//condition-3
		
		if(!helper.isNullOrEmpty(state)) {
		 condtionsList.add(cb.equal(root.get("state"), state));
		}

		//condition-5-search
		if(!helper.isNullOrEmpty(searchBy) && !helper.isNullOrEmpty(search)) {
		  condtionsList.add(cb.like(root.get("employee").get(searchBy), "%" + search + "%"));
		}
		
				
		//condition -6-sort
		if(!helper.isNullOrEmpty(sort) && !helper.isNullOrEmpty(sortBy)) {
			if(sort!=null && sort.equalsIgnoreCase("asc")) {
			    cq.orderBy(cb.asc(root.get("employee").get(sortBy)));
			}else {
				cq.orderBy(cb.desc(root.get("employee").get(sortBy)));
			}
	   }
		
		
		cq.where(cb.and(condtionsList.toArray(new Predicate[condtionsList.size()])));
		
		if(itemPerPage>0 && pageNumber>0) {
			final TypedQuery<Address> typedQuery = em.createQuery(cq);
			typedQuery.setFirstResult((pageNumber - 1) * itemPerPage);
			typedQuery.setMaxResults(itemPerPage);
			return typedQuery.getResultList();
		}
				
		
		//step-5
		TypedQuery<Address> typedQuery = em.createQuery(criteriaQuery);
		List<Address> allitems = typedQuery.getResultList();

		return allitems;
	}




	
	
	@Override
	public long getCount(String city, String state, int age, String search, String searchBy, String sort, String sortBy, int itemPerPage, int pageNumber) {
		//step-2
				CriteriaBuilder cb = em.getCriteriaBuilder();

				//step-3
				//change-1st
				CriteriaQuery<Long> cq = cb.createQuery(Long.class);
				Root<Address> root = cq.from(Address.class);
				
				//change-2nd
				cq.select(cb.count(root));

				
				//step-4 fir condition.
				
				List<Predicate> condtionsList = new ArrayList<Predicate>();
				//condition-1
				
				if(!helper.isNullOrEmpty(city)) {
				 condtionsList.add(cb.equal(root.get("city") ,city));
				}
				//condition-2
				
				if(age>0) {
				 condtionsList.add(cb.equal(root.get("employee").get("age"), age));
				}
				//condition-3
				
				if(!helper.isNullOrEmpty(state)) {
				 condtionsList.add(cb.equal(root.get("state"), state));
				}

				//condition-5-search
				if(!helper.isNullOrEmpty(searchBy) && !helper.isNullOrEmpty(search)) {
				  condtionsList.add(cb.like(root.get("employee").get(searchBy), "%" + search + "%"));
				}
				
						
				//condition -6-sort
				if(!helper.isNullOrEmpty(sort) && !helper.isNullOrEmpty(sortBy)) {
					if(sort!=null && sort.equalsIgnoreCase("asc")) {
					    cq.orderBy(cb.asc(root.get("employee").get(sortBy)));
					}else {
						cq.orderBy(cb.desc(root.get("employee").get(sortBy)));
					}
			   }
				

				cq.where(cb.and(condtionsList.toArray(new Predicate[condtionsList.size()])));
				
				//change-3rd				
				return em.createQuery(cq).getSingleResult();

	}
	

}
