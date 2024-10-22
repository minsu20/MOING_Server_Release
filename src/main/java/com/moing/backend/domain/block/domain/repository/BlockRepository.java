package com.moing.backend.domain.block.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.moing.backend.domain.block.domain.entity.Block;

public interface BlockRepository extends JpaRepository<Block, Long>, BlockCustomRepository {

}
