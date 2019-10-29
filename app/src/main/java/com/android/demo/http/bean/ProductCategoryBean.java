package com.android.demo.http.bean;

import java.util.List;

public class ProductCategoryBean {


    private List<ChildProductCategoryListBean> childProductCategoryList;

    public List<ChildProductCategoryListBean> getChildProductCategoryList() {
        return childProductCategoryList;
    }

    public void setChildProductCategoryList(List<ChildProductCategoryListBean> childProductCategoryList) {
        this.childProductCategoryList = childProductCategoryList;
    }

    public static class ChildProductCategoryListBean {
        /**
         * parentName : 数码
         * parentId : 0
         * categoryCode : 1001
         * childCategoryList : [{"categoryId":4,"categoryName":"手机","categoryCode":"1001001","parentId":1},{"categoryId":5,"categoryName":"电脑","categoryCode":"1001002","parentId":1}]
         */

        private String parentName;
        private int parentId;
        private String categoryCode;
        private List<ChildCategoryListBean> childCategoryList;

        public String getParentName() {
            return parentName;
        }

        public void setParentName(String parentName) {
            this.parentName = parentName;
        }

        public int getParentId() {
            return parentId;
        }

        public void setParentId(int parentId) {
            this.parentId = parentId;
        }

        public String getCategoryCode() {
            return categoryCode;
        }

        public void setCategoryCode(String categoryCode) {
            this.categoryCode = categoryCode;
        }

        public List<ChildCategoryListBean> getChildCategoryList() {
            return childCategoryList;
        }

        public void setChildCategoryList(List<ChildCategoryListBean> childCategoryList) {
            this.childCategoryList = childCategoryList;
        }

        public static class ChildCategoryListBean {
            /**
             * categoryId : 4
             * categoryName : 手机
             * categoryCode : 1001001
             * parentId : 1
             */

            private int categoryId;
            private String categoryName;
            private String categoryCode;
            private int parentId;

            public int getCategoryId() {
                return categoryId;
            }

            public void setCategoryId(int categoryId) {
                this.categoryId = categoryId;
            }

            public String getCategoryName() {
                return categoryName;
            }

            public void setCategoryName(String categoryName) {
                this.categoryName = categoryName;
            }

            public String getCategoryCode() {
                return categoryCode;
            }

            public void setCategoryCode(String categoryCode) {
                this.categoryCode = categoryCode;
            }

            public int getParentId() {
                return parentId;
            }

            public void setParentId(int parentId) {
                this.parentId = parentId;
            }
        }
    }
}
