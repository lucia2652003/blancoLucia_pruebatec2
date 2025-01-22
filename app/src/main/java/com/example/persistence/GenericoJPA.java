package com.example.persistence;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.util.List;
import java.util.Optional;

//Realizarán las operaciones CRUD de BD Turno
public class GenericoJPA<T> {

    private Class<T> entidadGenerica;//Cualquier Tipo de dato

    public GenericoJPA(Class<T> entidadGenerica) {
        this.entidadGenerica = entidadGenerica;
    }

    public void createGenerico(T nueva) {
        EntityManager em = ConfigJPA.getEntityManagerFactory();

        try{
            em.getTransaction().begin();
            //Guardar el registro en la tabla
            em.persist(nueva);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }finally {
            em.close();
        }
    }

    public Optional<T> findOneGenerico(Integer id){
        EntityManager em = ConfigJPA.getEntityManagerFactory();
        try {
            return Optional.ofNullable(em.find(this.entidadGenerica,id));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            em.close();
        }
        return Optional.empty();
    }

    public List<T> findAllGenerico(){
        EntityManager em = ConfigJPA.getEntityManagerFactory();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(this.entidadGenerica);
        Root<T> root = cq.from(this.entidadGenerica);
        CriteriaQuery<T> all = cq.select(root);
        TypedQuery<T> allQuery = em.createQuery(all);
        return allQuery.getResultList();
    }

    public void updateGenerico(T actializarEntidad){
        EntityManager em = ConfigJPA.getEntityManagerFactory();
        try {
            em.getTransaction().begin();
            em.merge(actializarEntidad);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }finally {
            em.close();
        }
    }

    public void deleteGenerico(Integer id){
        EntityManager em = ConfigJPA.getEntityManagerFactory();
        try{
            em.getTransaction().begin();
            Optional<T> elementoEncontrado = Optional.ofNullable(em.find(this.entidadGenerica,id));
            if (elementoEncontrado.isPresent()){
                em.remove(elementoEncontrado.get());
            }else{
                System.out.println("El id "+id+" de la tabla "+this.entidadGenerica.getSimpleName());
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }finally {
            em.close();
        }
    }
}
