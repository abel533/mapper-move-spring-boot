package io.mybatis.demo.custom.seq;

import cn.hutool.core.lang.Assert;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 列表顺序上下移动操作
 *
 * @author liuzh
 */
public class MoveMapperUtil {

    /**
     * 列表顺序上下移动操作
     *
     * @param mapper     dao 实例，需要继承 MoveMapper
     * @param movingId   被移动节点的 id
     * @param targetId   移动的目标 id
     * @param direction  移动的位置，上方或者下方
     * @param conditions 额外条件, 会追加到 where 查询条件，例如 name=1 and age=20
     * @param <M>
     * @return 结果0时所有顺序都没变化，相邻节点时交换位置时结果2，其他情况下>2
     */
    public static <M extends MoveMapper> int move(M mapper, Long movingId, Long targetId, Direction direction, Map<String, Object> conditions) {
        Assert.notNull(mapper);
        Assert.notNull(movingId);
        Assert.notNull(targetId);
        Assert.notNull(direction);
        //移动操作影响的行数
        int resultCount = 0;
        //获取两个ID对应的顺序号
        List<SeqModel> seqs = mapper.getSeqs(movingId + "," + targetId);
        Assert.isTrue(seqs.size() == 2);
        Map<Long, Long> idSeqMap = seqs.stream().collect(Collectors.toMap(SeqModel::getId, SeqModel::getSeq));
        Long movingSeq = idSeqMap.get(movingId);
        Long targetSeq = idSeqMap.get(targetId);
        Assert.notNull(movingSeq);
        Assert.notNull(targetSeq);
        MoveDirection position = null;
        switch (direction) {
            case AFTER:
                position = movingSeq < targetSeq  /* true - 向下移动*/ ? MoveDirection.DownAfter : MoveDirection.UpAfter;
                break;
            case BEFORE:
                position = movingSeq < targetSeq  /*true - 向下移动*/ ? MoveDirection.DownBefore : MoveDirection.UpBefore;
                break;
            default:
        }
        Assert.notNull(position);
        //先更新区间范围内的顺序，相邻节点时可能更新0或1条数据，不相邻时>=1条
        if (Math.abs(movingSeq - targetSeq) == 1) {
            //相邻节点时，只有两种情况需要交换位置，下面的 SQL 只会更新 target 顺序
            if (position == MoveDirection.DownAfter
                    || position == MoveDirection.UpBefore) {
                resultCount += mapper.move(movingSeq, targetSeq, position, conditions);
            }
        } else {//不相邻时，中间阶段顺序一定会变
            resultCount += mapper.move(movingSeq, targetSeq, position, conditions);
        }
        //根据位置更新移动节点
        Long newSeq = movingSeq;
        switch (position) {
            case DownBefore:
                newSeq = targetSeq - 1;
                break;
            case DownAfter:
            case UpBefore:
                newSeq = targetSeq;
                break;
            case UpAfter:
                newSeq = targetSeq + 1;
                break;
            default:
        }
        //当移动节点的顺序变化时更新（例外情况，向下移动到下一个节点的上方，此时位置不变）
        if (!newSeq.equals(movingSeq)) {
            resultCount += mapper.updateSeqById(movingId, newSeq);
        }
        return resultCount;
    }

}
