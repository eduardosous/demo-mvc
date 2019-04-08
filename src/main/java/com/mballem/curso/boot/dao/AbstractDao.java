package com.mballem.curso.boot.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

public abstract class AbstractDao<T, PK extends Serializable> {

	/**
	 * O atributo entityClass vai ser responsável por fornecer a classe referente ao
	 * tipo de entidade que está sendo persistida. Por exemplo, ao persistir um
	 * objeto cargo, o tipo de classe de objeto é Cargo.class. Essa informação é
	 * importante principalmente para as operações de leitura. Essa informação é
	 * então recuperada a partir da API Reflection do Java.
	 */
	@SuppressWarnings("unchecked")
	private final Class<T> entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
			.getActualTypeArguments()[0];

	/**
	 * Um ponto importante é o acesso ao objeto EntityManager da JPA, o qual é
	 * injetado na classe via anotação @PersistenceContext da própria especificação
	 * JPA.
	 */
	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * E o método getEntityManager() vai retornar uma instancia de EntityManager
	 * para qualquer classe de DAO especifico que fizer a chamada a ele. Assim, se
	 * for necessário criar um novo método de CRUD, que não seja fornecido pela
	 * classe AbstractDao, ele poderá ser criado especificamente na classe concreta
	 * ClasseDao
	 * 
	 */
	protected EntityManager geEntityManager() {
		return entityManager;
	}

	public void save(T entity) {
		entityManager.persist(entity);
	}

	public void update(T entity) {
		entityManager.merge(entity);
	}

	public void delete(PK id) {
		entityManager.remove(entityManager.getReference(entityClass, id));
	}

	public T findById(PK id) {
		return entityManager.find(entityClass, id);
	}

	public List<T> findAll() {
		return entityManager.createQuery("from " + entityClass.getSimpleName(), entityClass).getResultList();
	}

	protected List<T> createQuery(String jpql, Object... params) {
		TypedQuery<T> query = entityManager.createQuery(jpql, entityClass);
		for (int i = 0; i < params.length; i++) {
			query.setParameter(i + 1, params[i]);
		}
		return query.getResultList();
	}

}
