import React from 'react';
import styled from 'styled-components';

const FooterContainer = styled.footer`
  background-color: #222;
  color: #ccc;
  padding: 20px 0;
`;

const FooterContent = styled.div`
  display: flex;
  justify-content: space-around;
  align-items: flex-start;
  padding: 20px;
  max-width: 1200px;
  margin: 0 auto;

  @media (max-width: 600px) {
    flex-direction: column;
    align-items: center;
    gap: 40px;
  }
`;

const FooterSection = styled.div`
  flex: 1;
  margin: 0 10px;

  @media (max-width: 600px) {
    display: flex;
    flex-direction: column;
    align-items: center;
    text-align: center;
  }
`;

const FooterTitle = styled.h4`
  color: #fff;
  margin-bottom: 10px;
  font-weight: bold;
`;

const FooterText = styled.p`
  color: #ccc;
`;

const FooterList = styled.ul`
  list-style: none;
  padding: 0;
`;

const FooterListItem = styled.li`
  margin-bottom: 8px;
`;

const FooterLink = styled.a`
  color: #ccc;
  text-decoration: none;
  transition: color 0.3s;

  &:hover {
    color: #fff;
  }
`;

const SocialIcons = styled.div`
  display: flex;
  gap: 10px;
`;

const FooterBottom = styled.div`
  text-align: center;
  padding-top: 10px;
  border-top: 1px solid #555;
  margin-top: 20px;
`;

const Footer: React.FC = () => {
  return (
    <FooterContainer>
      <FooterContent>
        <FooterSection>
          <FooterTitle>About Us</FooterTitle>
          <FooterText>
            JSA Services are a group of friends who usually do things individually, but we shine when we do things together
          </FooterText>
        </FooterSection>

        <FooterSection>
          <FooterTitle>Quick Links</FooterTitle>
          <FooterList>
            <FooterListItem><FooterLink href="#home">Home</FooterLink></FooterListItem>
            <FooterListItem><FooterLink href="#services">Services</FooterLink></FooterListItem>
            <FooterListItem><FooterLink href="#companies">Companies</FooterLink></FooterListItem>
          </FooterList>
        </FooterSection>

        <FooterSection>
          <FooterTitle>Contact Us</FooterTitle>
          <FooterText><i className="fas fa-phone"></i> +57 (111) 111-1111</FooterText>
          <FooterText><i className="fas fa-envelope"></i> info@jsaServices.com</FooterText>
        </FooterSection>

        <FooterSection>
          <FooterTitle>Follow Us</FooterTitle>
          <SocialIcons>
            <FooterLink href="#"><i className="fab fa-facebook-f"></i></FooterLink>
            <FooterLink href="#"><i className="fab fa-instagram"></i></FooterLink>
            <FooterLink href="#"><i className="fab fa-twitter"></i></FooterLink>
          </SocialIcons>
        </FooterSection>
      </FooterContent>

      <FooterBottom>
        <FooterText>© 2024 JSA Services. All rights reserved.</FooterText>
      </FooterBottom>
    </FooterContainer>
  );
};

export default Footer;