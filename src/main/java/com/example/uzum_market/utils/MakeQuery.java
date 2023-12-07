//package com.example.uzum_market.utils;
//
//import com.example.uzum_market.dto.*;
//import com.example.uzum_market.enums.ComparatorTypeEnum;
//
//import java.sql.Timestamp;
//import java.util.List;
//
//import static com.example.uzum_market.enums.ComparatorTypeEnum.*;
//
//public class MakeQuery {
//    public static String makeQuery(MainCriteriaDTO mainCriteriaDTO, String tableName, Integer id) {
//        StringBuilder sb = new StringBuilder(250);
//        sb.append("SELECT id FROM ");
//        sb.append(tableName);
//        sb.append(" ");
//        FilterDTO filter = mainCriteriaDTO.getFilter();
//        boolean isSearching = filter.getSearch() != null && !filter.getSearch().isEmpty();
//        boolean isFiltering = filter.getFilterFields() != null;
//
//        if (isFiltering || isSearching || id !=null) {
//            sb.append("WHERE ");
//        }
//        if(id != null){
//            sb.append("category_id = ");
//            sb.append(id);
//            sb.append(" ");
//        }
//        addSearchingQuery(sb, filter, isSearching);
//        addFilterQuery(sb, filter, isFiltering);
//        addOrderByPart(mainCriteriaDTO, sb);
//        addOffsetPart(mainCriteriaDTO, sb);
//        return sb.toString();
//    }
//
//    private static void addOffsetPart(MainCriteriaDTO mainCriteriaDTO, StringBuilder sb) {
//        sb.append("LIMIT ")
//                .append(mainCriteriaDTO.getSize())
//                .append(" OFFSET ")
//                .append(mainCriteriaDTO.getPage() * mainCriteriaDTO.getSize());
//    }
//
//    private static void addOrderByPart(MainCriteriaDTO mainCriteriaDTO, StringBuilder sb) {
//        List<SortDTO> sorts = mainCriteriaDTO.getSorts();
//        if (sorts != null && !sorts.isEmpty()) {
//            sb.append("ORDER BY ");
//            for (SortDTO sort : sorts)
//                sb.append(sort.getColumn())
//                        .append(" ")
//                        .append(sort.getDirection())
//                        .append(",");
//            sb.replace(sb.length() - 1, sb.length(), " ");
//        }
//    }
//
//    private static void addFilterQuery(StringBuilder sb, FilterDTO filter, boolean isFiltering) {
//        if (isFiltering) {
//            sb.append("AND (");
//            List<FilterField> fields = filter.getFilterFields();
//            for (int i = 0; i < fields.size(); i++) {
//                if (i != 0)
//                    sb.append(filter.getFilterOperator());
//
//                sb.append("(");
//                FilterField filterField = fields.get(i);
//                ComparatorTypeEnum comparatorType = filterField.getComparatorType();
//                FilterFieldValue fieldValue = filterField.getValue();
//                openedByColumnType(sb, comparatorType, fieldValue, filterField);
//                sb.append(")");
//            }
//            sb.append(") ");
//        }
//    }
//
//    private static void openedByColumnType(StringBuilder sb, ComparatorTypeEnum comparatorType, FilterFieldValue fieldValue, FilterField field) {
//        switch (field.getColumnType()) {
//            case SHORT_TEXT -> appendForShortText(sb, field, comparatorType, fieldValue);
//            case MONEY, NUMBER -> appendForNumberOrMoney(sb, field, comparatorType, fieldValue);
//            case FILE -> appendForFile(sb, field, comparatorType);
//            case DATE -> appendForDate(sb, field, comparatorType, fieldValue);
//
//        }
//    }
//
//    private static void appendForDate(StringBuilder sb, FilterField field, ComparatorTypeEnum comparatorType, FilterFieldValue fieldValue) {
//        switch (comparatorType) {
//            case LT -> {
//                sb.append(field.getColumn());
//                sb.append("<'")
//                        .append( new Timestamp(fieldValue.getStartDate()))
//                        .append("'");
//            }
//            case LTE -> {
//                sb.append(field.getColumn());
//                sb.append("<='")
//                        .append( new Timestamp(fieldValue.getStartDate()))
//                        .append("'");
//            }
//            case GT -> {
//                sb.append(field.getColumn());
//                sb.append(">'")
//                        .append( new Timestamp(fieldValue.getStartDate()))
//                        .append("'");
//            }
//            case GTE -> {
//                sb.append(field.getColumn());
//                sb.append(">='")
//                        .append( new Timestamp(fieldValue.getStartDate()))
//                        .append("'");
//            }
//            case RA -> {
//                sb.append(field.getColumn());
//                sb.append(">='")
//                        .append( new Timestamp(fieldValue.getStartDate()))
//                        .append("' AND ");
//                sb.append(field.getColumn());
//                sb.append("<='")
//                        .append( new Timestamp(fieldValue.getEndDate()))
//                        .append("'");
//            }
//        }
//    }
//
//    private static void appendForFile(StringBuilder sb, FilterField field, ComparatorTypeEnum comparatorType) {
//        if ( comparatorType.equals(IS_SET) || comparatorType.equals(IS_NOT_SET) ) {
//                sb.append(field.getColumn());
//                sb.append(" IS ");
//                if (comparatorType.equals(ComparatorTypeEnum.IS_SET))
//                    sb.append("NOT ");
//                sb.append("NULL ");
//            }
//    }
//
//    private static void appendForNumberOrMoney(StringBuilder sb, FilterField field, ComparatorTypeEnum comparatorType, FilterFieldValue fieldValue) {
//        switch (comparatorType) {
//            case EQ -> {
//                sb.append(field.getColumn());
//                sb.append("=");
//                sb.append(fieldValue);
//            }
//            case NOT -> {
//                sb.append(field.getColumn());
//                sb.append("!=");
//                sb.append(fieldValue);
//            }
//            case LT -> {
//                sb.append(field.getColumn());
//                sb.append("<");
//                sb.append(field.getValue().getSearchingValue());
//            }
//            case LTE -> {
//                sb.append(field.getColumn());
//                sb.append("<=");
//                sb.append(field.getValue().getSearchingValue());
//            }
//            case GT -> {
//                sb.append(field.getColumn());
//                sb.append(">");
//                sb.append(field.getValue().getSearchingValue());
//            }
//            case GTE -> {
//                sb.append(field.getColumn());
//                sb.append(">=");
//                sb.append(field.getValue().getSearchingValue());
//            }
//            case RA -> {
//                sb.append(field.getColumn());
//                sb.append(">=");
//                sb.append(field.getValue().getMinValue());
//                sb.append(" AND ");
//                sb.append(field.getColumn());
//                sb.append("<=");
//                sb.append(field.getValue().getMaxValue());
//            }
//        }
//    }
//
//    private static void appendForShortText(StringBuilder sb, FilterField field, ComparatorTypeEnum comparatorType, FilterFieldValue fieldValue) {
//        if ( comparatorType.equals(EQ) || comparatorType.equals(NOT) ) {
//                sb.append(field.getColumn());
//                if (comparatorType.equals(NOT)) {
//                    sb.append("!");
//                }
//                sb.append(" ~* '");
//                sb.append(fieldValue.getSearchingValue());
//                sb.append("'");
//            }
//            if ( comparatorType.equals(IS_SET) || comparatorType.equals(IS_NOT_SET) ) {
//                sb.append(field.getColumn());
//                sb.append(" IS ");
//                if (comparatorType.equals(ComparatorTypeEnum.IS_NOT_SET)) sb.append("NOT ");
//                sb.append("NULL ");
//            }
//
//    }
//
//    private static void addSearchingQuery(StringBuilder sb, FilterDTO filter, boolean isSearching) {
//        if (isSearching) {
//            String search = filter.getSearch();
//            sb.append(" AND (");
//            List<String> searchingColumns = filter.getSearchingColumns();
//            for (int i = 0; i < searchingColumns.size(); i++) {
//                if (i != 0) sb.append(" OR ");
//                String column = searchingColumns.get(i);
//                sb.append(column);
//                sb.append(" ~* '");
//                sb.append(search);
//                sb.append("'");
//            }
//            sb.append(") ");
//
//        }
//    }
//}
