import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    DashboardLayout()
}

@Composable
fun DashboardLayout() {
    Row(modifier = Modifier.fillMaxSize()) {
        Column(
            Modifier.fillMaxHeight()
                .width(100.dp)
                .background(Color.White)
                .padding(horizontal = 5.dp, vertical = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            MenuItem(Icons.Default.Home, "Home")
            MenuItem(Icons.Default.Person, "Profile")
            MenuItem(Icons.Default.Settings, "Settings")
            MenuItem(Icons.Default.Info, "About Us")
            MenuItem(Icons.Default.Call, "Contact Us")

        }
        Column(
            modifier = Modifier
                .weight(1f) // Sesuai dengan flex-1
                .fillMaxHeight()
                .background(Color(0xFFf5f5f5)) // Sesuai dengan bg-green-100
                .padding(10.dp)
        ) {
            SearchBar()
            PreviewProductGrid()
        }
        Column(
            Modifier.fillMaxHeight()
                .width(360.dp)
                .background(Color.White)
                .padding(horizontal = 5.dp, vertical = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            CartList()
            CartTotal()
        }
    }
}

@Composable
fun ProductCard(
    title: String,
    description: String,
    price: String,
    onClick: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(8.dp),
        elevation = 4.dp
    ) {
        Column {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = title, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                Text(text = description, fontSize = 14.sp, color = Color.Gray)
                Text(text = price, fontSize = 16.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
fun ProductGrid(products: List<Product>, onProductClick: (Product) -> Unit) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 200.dp),
        contentPadding = PaddingValues(8.dp)
    ) {
        items(products) { product ->
            ProductCard(
                title = product.title,
                description = product.description,
                price = product.price,
                onClick = { onProductClick(product) }
            )
        }
    }
}

data class Product(
    val imageUrl: String,
    val title: String,
    val description: String,
    val price: String
)

@Composable
fun PreviewProductGrid() {
    val products = listOf(
        Product("https://via.placeholder.com/300", "Product 1", "This is a description of product 1.", "$19.99"),
        Product("https://via.placeholder.com/300", "Product 2", "This is a description of product 2.", "$29.99"),
        Product("https://via.placeholder.com/300", "Product 3", "This is a description of product 3.", "$39.99"),
        Product("https://via.placeholder.com/300", "Product 3", "This is a description of product 3.", "$39.99"),
        Product("https://via.placeholder.com/300", "Product 3", "This is a description of product 3.", "$39.99"),
        Product("https://via.placeholder.com/300", "Product 3", "This is a description of product 3.", "$39.99"),
        Product("https://via.placeholder.com/300", "Product 3", "This is a description of product 3.", "$39.99"),
        Product("https://via.placeholder.com/300", "Product 3", "This is a description of product 3.", "$39.99"),
        Product("https://via.placeholder.com/300", "Product 3", "This is a description of product 3.", "$39.99"),
        Product("https://via.placeholder.com/300", "Product 3", "This is a description of product 3.", "$39.99"),
        Product("https://via.placeholder.com/300", "Product 3", "This is a description of product 3.", "$39.99"),
        Product("https://via.placeholder.com/300", "Product 3", "This is a description of product 3.", "$39.99"),
        Product("https://via.placeholder.com/300", "Product 3", "This is a description of product 3.", "$39.99"),
        Product("https://via.placeholder.com/300", "Product 3", "This is a description of product 3.", "$39.99"),
        Product("https://via.placeholder.com/300", "Product 3", "This is a description of product 3.", "$39.99"),
        Product("https://via.placeholder.com/300", "Product 3", "This is a description of product 3.", "$39.99"),
        Product("https://via.placeholder.com/300", "Product 3", "This is a description of product 3.", "$39.99"),
        Product("https://via.placeholder.com/300", "Product 3", "This is a description of product 3.", "$39.99"),
        Product("https://via.placeholder.com/300", "Product 3", "This is a description of product 3.", "$39.99"),
        Product("https://via.placeholder.com/300", "Product 3", "This is a description of product 3.", "$39.99"),
        Product("https://via.placeholder.com/300", "Product 3", "This is a description of product 3.", "$39.99"),
        Product("https://via.placeholder.com/300", "Product 3", "This is a description of product 3.", "$39.99"),
        Product("https://via.placeholder.com/300", "Product 3", "This is a description of product 3.", "$39.99"),
        Product("https://via.placeholder.com/300", "Product 3", "This is a description of product 3.", "$39.99"),
        Product("https://via.placeholder.com/300", "Product 3", "This is a description of product 3.", "$39.99"),
        Product("https://via.placeholder.com/300", "Product 3", "This is a description of product 3.", "$39.99"),
        Product("https://via.placeholder.com/300", "Product 4", "This is a description of product 4.", "$49.99")
    )
    ProductGrid(products) { product ->
        // Handle product click
    }
}


@Composable
fun CartTotal() {
    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Total")
            Text(text = "Rp.40.000,-", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        }
        Button(
            onClick = {
                println("Halo dunia")
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Proses")
        }
    }
}

@Composable
fun CartList() {
    Column{
        CartItem()
        CartItem()
        CartItem()
    }
}

@Composable
fun CartItem() {
    Column {
        Row(
            verticalAlignment = Alignment.Top,
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Column {
                Text(text = "Chocochips")
                Text(text = "Rp.40.000,-")
            }
            Row {
                Counter()
                Spacer(Modifier.width(10.dp))
                Text(text = "Rp.40.000,-")
            }
        }
        Box(Modifier.height(1.dp).background(Color.Gray).fillMaxWidth())
    }
}

@Composable
fun Counter() {
    var count by remember { mutableStateOf(10) }

    Row(
        modifier = Modifier
            .wrapContentWidth()
            .background(Color.Transparent),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Box(
            modifier = Modifier
                .width(20.dp)
                .height(20.dp)
                .background(Color(0xFFf5f5f5), shape = RoundedCornerShape(4.dp)).clickable {
                    if (count > 0) count--
                },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Remove,
                contentDescription = "Decrease",
                tint = Color(0xFF95969c),
                modifier = Modifier.size(20.dp)
            )
        }
        Text(
            text = "$count",
            fontSize = 18.sp,
            modifier = Modifier.padding(horizontal = 2.dp)
        )
        Box(
            modifier = Modifier
                .width(20.dp)
                .height(20.dp)
                .background(Color(0xFFf5f5f5), shape = RoundedCornerShape(4.dp)).clickable {
                    if (count > 0) count++
                },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Increase",
                tint = Color(0xFF95969c),
                modifier = Modifier.size(20.dp)
            )
        }
    }
}

@Composable
fun SearchBar() {
    var searchText by remember { mutableStateOf(TextFieldValue("")) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White, shape = RoundedCornerShape(8.dp))
            .padding(horizontal = 16.dp, vertical = 8.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null,
                tint = Color.Gray,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            BasicTextField(
                value = searchText,
                onValueChange = { searchText = it },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                textStyle = LocalTextStyle.current.copy(fontSize = 18.sp, color = Color.Black)
            ) {
                if (searchText.text.isEmpty()) {
                    Text(
                        text = "Search",
                        color = Color.Gray,
                        fontSize = 18.sp
                    )
                }
                it()
            }
        }
    }
}

@Composable
fun MenuItem(icon: ImageVector, title: String) {
    Column(
        Modifier.fillMaxWidth()
            .padding(bottom = 10.dp)
            .background(color = Color(0XFFfef2e8))
            .border(
                width = 1.dp,
                color = Color(0XFFFB811B),
                shape = RoundedCornerShape(8.dp)
            )
            .padding(vertical = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            icon,
            contentDescription = null,
            tint = Color(0XFFFB811B)
        )
        Spacer(modifier = Modifier.width(10.dp))
        Text(text = title, color = Color(0XFFFB811B))
    }
}
