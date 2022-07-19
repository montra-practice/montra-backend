package com.epam.dao;

import com.epam.data.SysLog;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @description:
 * @author: taoz
 * @date: 2022/7/14 9:51
 */
public interface SysLogRepository extends JpaRepository<SysLog, Long> {
}
