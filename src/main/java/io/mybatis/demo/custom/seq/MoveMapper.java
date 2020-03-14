package io.mybatis.demo.custom.seq;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import tk.mybatis.mapper.annotation.RegisterMapper;

import java.util.List;
import java.util.Map;

/**
 * 设置上下移动区间的序号更新
 *
 * @author liuzh
 */
@RegisterMapper
public interface MoveMapper<T> {
    /**
     * 1. 获取给定 ids 对应的顺序号
     *
     * @param ids
     * @return
     */
    @SelectProvider(type = MoveProvider.class, method = "dynamicSQL")
    List<SeqModel> getSeqs(@Param("ids") String ids);

    /**
     * 2. 移动节点调整中间部分的顺序，相邻节点调整时不需要调用当前方法
     *
     * @param movingSeq  移动节点序号
     * @param targetSeq  目标节点序号
     * @param direction  位置
     * @param conditions 额外条件
     * @return
     */
    @UpdateProvider(type = MoveProvider.class, method = "dynamicSQL")
    int move(@Param("movingSeq") Long movingSeq, @Param("targetSeq") Long targetSeq,
             @Param("direction") MoveDirection direction, @Param("conditions") Map<String, Object> conditions);

    /**
     * 3. 根据主键更新顺序号
     *
     * @param id
     * @param seq
     * @return
     */
    @UpdateProvider(type = MoveProvider.class, method = "dynamicSQL")
    int updateSeqById(@Param("id") Long id, @Param("seq") Long seq);

}
