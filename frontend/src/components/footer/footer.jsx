import './Footer.css';

function Footer() {
    return (
        <footer className="footer">
        <div className="footer-container">
            <div className="footer-section about-us">
            <h4>About Us</h4>
            <p class="description">
            JSA Services are a group of friends who usually do things individually, but we shine when we do things together
            </p>
            </div>

            <div className="footer-section quick-links">
            <h4>Quick Links</h4>
            <ul>
                <li><a href=".navbar">Home</a></li>
                <li><a href="#">Services</a></li>
                <li><a href="#">Companies</a></li>
            </ul>
            </div>

            <div className="footer-section contact-us">
            <h4>Contact Us</h4>
            <p><i className="fas fa-phone"></i> +57 (111) 111-1111</p>
            <p><i className="fas fa-envelope"></i> info@jsaServices.com</p>
            </div>

            <div className="footer-section follow-us">
            <h4>Follow Us</h4>
            <div className="social-icons">
                <a href="#"><i className="fab fa-facebook-f"></i></a>
                <a href="#"><i className="fab fa-instagram"></i></a>
                <a href="#"><i className="fab fa-twitter"></i></a>
            </div>
            </div>
        </div>

        <div className="footer-bottom">
            <p>© 2024 JSA Services. All rights reserved.</p>
        </div>
        </footer>
    );
}

export default Footer;
