package com.applicaton.internshipvk.entity

interface ProductList<P: Product> {
    val products: List<P>
}