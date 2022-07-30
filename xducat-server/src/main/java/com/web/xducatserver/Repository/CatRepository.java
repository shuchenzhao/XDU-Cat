package com.web.xducatserver.Repository;

import com.web.xducatserver.Entity.Cat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface CatRepository extends JpaRepository<Cat,Integer> {

    @Query(value="select * from cat where color like %?1%",nativeQuery=true)
    public List<Cat> findCatByColor(String color);//按照颜色查找猫咪

    @Query(value="select * from cat where id=?1",nativeQuery=true)
    public Cat findCatById(int id);//按照id查找猫咪

    @Query(value="select * from cat where id>=all(select id from cat)",nativeQuery=true)
    public Cat newestCat();//查找id最大的猫咪

    @Modifying
    @Transactional
    @Query(value="update cat set location=?2 where id=?1",nativeQuery=true)
    public void modifyCat(int id,String location);//修改猫咪信息

    @Modifying
    @Transactional
    @Query(value="insert into cat value(?1,?2,?3,?4)",nativeQuery=true)
    public void insertCat(int id, String location, String color, String img);//添加新的猫咪
}