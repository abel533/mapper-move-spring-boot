package io.mybatis.demo.custom.seq;

/**
 * 移动方向和位置
 */
public enum MoveDirection {
    /**
     * 向下移动到目标节点前（上）(source,target)-1
     */
    DownBefore,
    /**
     * 向下移动到目标节点后（下）(source,target]-1
     */
    DownAfter,
    /**
     * 向上移动到目标节点前（上）[target,source)+1
     */
    UpBefore,
    /**
     * 向上移动到目标节点后（下）(target,source)+1
     */
    UpAfter
}
