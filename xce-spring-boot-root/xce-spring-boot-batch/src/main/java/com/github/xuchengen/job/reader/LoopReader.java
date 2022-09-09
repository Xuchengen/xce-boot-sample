package com.github.xuchengen.job.reader;

import org.springframework.batch.item.data.RepositoryItemReader;

import java.util.List;

/**
 * <p>循环阅读器
 * <p>循环阅读器旨在循环分页读取，直到读取完所有记录。
 * <p>作者：徐承恩
 * <p>邮箱：<a href="mailto:xuchengen@gmail.com">xuchengen@gmail.com
 * <p>日期：2022-09-03 8:32
 **/
public class LoopReader<T> extends RepositoryItemReader<T> {

    @Override
    @SuppressWarnings("NullableProblems")
    protected List<T> doPageRead() throws Exception {
        // 举个例子：假设12条数据，每页3条，总共4页。
        // 第一次读：分页参数为：0页，3条，SQL语句[select * from t limit 3 offset 0]，结果集[1,2,3]，处理器处理3条数据并写入数据库。
        // 第二次读：分页参数为：1页，3条，SQL语句[select * from t limit 3 offset (1 * 3)]，结果集[7,8,9]，略过[4,5,6]主要矛盾就在这儿。
        // 通过该方法抑制分页参数使其始终读取满足查询条件的第一页，主要矛盾在于对当前表的读写，由于分页参数偏移导致部分数据直接略过。
        super.jumpToItem(0);
        return super.doPageRead();
    }
}
