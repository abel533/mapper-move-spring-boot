package io.mybatis.demo.custom.seq;

import org.apache.ibatis.mapping.MappedStatement;
import tk.mybatis.mapper.entity.EntityColumn;
import tk.mybatis.mapper.mapperhelper.EntityHelper;
import tk.mybatis.mapper.mapperhelper.MapperHelper;
import tk.mybatis.mapper.mapperhelper.MapperTemplate;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author liuzh
 */
public class MoveProvider extends MapperTemplate {

    public MoveProvider(Class<?> mapperClass, MapperHelper mapperHelper) {
        super(mapperClass, mapperHelper);
    }

    /**
     * 获取 Seq 顺序号列名
     *
     * @param entityClass
     * @return
     */
    private String getSeqColumnName(Class<?> entityClass) {
        //获取全部列
        Set<EntityColumn> columnList = EntityHelper.getColumns(entityClass);
        List<EntityColumn> seqColumns = columnList.stream().filter(column -> column.getEntityField().isAnnotationPresent(Seq.class)).collect(Collectors.toList());
        if (seqColumns.size() == 0) {
            throw new RuntimeException(entityClass.getCanonicalName() + " 类中不存在带有 @Seq 注解的顺序号字段");
        } else if (seqColumns.size() > 1) {
            throw new RuntimeException(entityClass.getCanonicalName() + " 类中存在多个带有 @Seq 注解的顺序号字段");
        }
        return seqColumns.get(0).getColumn();
    }

    /**
     * 移动
     *
     * @param ms
     * @return
     */
    public String move(MappedStatement ms) {
        Class<?> entityClass = this.getEntityClass(ms);
        StringBuilder sql = new StringBuilder();
        //获取 Seq 顺序号列名
        String seqColumnName = getSeqColumnName(entityClass);
        sql.append("update ").append(tableName(entityClass))
                .append(" <choose>")
                .append(" <when test=\"direction == @io.mybatis.demo.custom.seq.MoveDirection@DownBefore\">")
                .append(" set " + seqColumnName + " = " + seqColumnName + " - 1 where " + seqColumnName + " &gt; #{movingSeq} and " + seqColumnName + " &lt; #{targetSeq}")
                .append(" </when>")
                .append(" <when test=\"direction == @io.mybatis.demo.custom.seq.MoveDirection@DownAfter\">")
                .append(" set " + seqColumnName + " = " + seqColumnName + " - 1 where " + seqColumnName + " &gt; #{movingSeq} and " + seqColumnName + " &lt;= #{targetSeq}")
                .append(" </when>")
                .append(" <when test=\"direction == @io.mybatis.demo.custom.seq.MoveDirection@UpBefore\">")
                .append(" set " + seqColumnName + " = " + seqColumnName + " + 1 where " + seqColumnName + " &gt;= #{targetSeq} and " + seqColumnName + " &lt; #{movingSeq}")
                .append(" </when>")
                .append(" <when test=\"direction == @io.mybatis.demo.custom.seq.MoveDirection@UpAfter\">")
                .append(" set " + seqColumnName + " = " + seqColumnName + " + 1 where " + seqColumnName + " &gt; #{targetSeq} and " + seqColumnName + " &lt; #{movingSeq}")
                .append(" </when>")
                .append(" </choose>");
        //追加其他 where 条件
        sql.append(" <foreach collection=\"conditions\" item=\"val\" index=\"colName\">");
        sql.append(" and ${colName} = #{val}");
        sql.append(" </foreach>");
        return sql.toString();
    }

    /**
     * 获取指定 ids 的序号
     *
     * @param ms
     * @return
     */
    public String getSeqs(MappedStatement ms) {
        Class<?> entityClass = this.getEntityClass(ms);
        StringBuilder sql = new StringBuilder();
        //获取 Seq 顺序号列名
        String seqColumnName = getSeqColumnName(entityClass);
        sql.append("select id, ").append(seqColumnName).append(" as seq from ").append(tableName(entityClass)).append(" where id in ( ${ids})");
        return sql.toString();
    }

    /**
     * 根据 id 更新序号
     *
     * @param ms
     * @return
     */
    public String updateSeqById(MappedStatement ms) {
        Class<?> entityClass = this.getEntityClass(ms);
        StringBuilder sql = new StringBuilder();
        //获取 Seq 顺序号列名
        String seqColumnName = getSeqColumnName(entityClass);
        sql.append("update ").append(tableName(entityClass)).append(" set ").append(seqColumnName).append(" = #{seq} where id = #{id}");
        return sql.toString();
    }

}
