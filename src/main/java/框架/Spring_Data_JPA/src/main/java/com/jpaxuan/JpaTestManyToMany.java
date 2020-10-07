package com.jpaxuan;

import com.jpaxuan.pojo.Category;
import com.jpaxuan.pojo.Item;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * @Author: 轩轩
 * @Date: 2020/10/6 16:10
 * @description:
 */
public class JpaTestManyToMany {
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;
    private EntityTransaction entityTransaction;


    @Before
    public void init(){
        entityManagerFactory = Persistence.createEntityManagerFactory("jpa_test");
        entityManager = entityManagerFactory.createEntityManager();
        entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();//开启事务.
    }

    @After
    public void destroy(){
        entityTransaction.commit();//提交事务

        //关闭管理器和工厂
        entityManager.close();
        entityManagerFactory.close();
    }

    @Test
    public void testManyToManY(){
        Item i1 = new Item();
        i1.setItemName("i-1");

        Item i2 = new Item();
        i2.setItemName("i-2");

        Category c1 = new Category();
        c1.setCategoryName("c-1");

        Category c2 = new Category();
        c2.setCategoryName("c-2");

        //设置关联关系
        i1.getCategories().add(c1);
        i2.getCategories().add(c2);
        c1.getItems().add(i1);
        c2.getItems().add(i2);

        //执行保存

        entityManager.persist(i1);
        entityManager.persist(i2);
        entityManager.persist(c1);
        entityManager.persist(c2);
    }


    /**
     * 对于关联的对象，默认使用懒加载的方式
     *
     * 使用维护关系的一方获取，还是使用不维护关联关系的一方获取   sql 语句是相同的
     */
    @Test
    public void testManyToManyFind(){
//        Item item = entityManager.find(Item.class, 70);
//        System.out.println(item.getItemName());
//        System.out.println(item.getCategories().size());



        //不维护的一方
        Category category = entityManager.find(Category.class, 72);
        System.out.println(category.getCategoryName());
        System.out.println(category.getItems().size());
    }

}
