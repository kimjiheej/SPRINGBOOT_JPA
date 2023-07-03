package jpabook.jpashop.domain.item;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Category {

    @Id
    @GeneratedValue
    @Column(name="category_id")
    private Long id;

    private String name;

    @ManyToMany
    @JoinTable(name="category_item", joinColumns = @JoinColumn(name="category_id"), inverseJoinColumns = @JoinColumn(name="item_id")) // 관계형 db 는 1:다 다:1 로 풀어내는 중간 테이블이 필요하다
    private List<Item> items = new ArrayList<>();

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="parent_id")
    private Category parent;

    @OneToMany(mappedBy="parent")
    private List<Category> child = new ArrayList<>();


    //연관관계 메서드라고 한다
    public void addChildCategory(Category child){
        this.child.add(child);
        child.setParent(this);
    }

}
