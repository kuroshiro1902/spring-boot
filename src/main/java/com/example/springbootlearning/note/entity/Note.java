package com.example.springbootlearning.note.entity;

import com.example.springbootlearning.common.entity.Base;
import com.example.springbootlearning.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "notes")
@Getter
@Setter
public class Note extends Base {

  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false)
  User user;

  @Column(length = 1000)
  String title;

  @Column(columnDefinition = "TEXT")
  String content;
}
