
import './Footer.css'

export default function Footer() {
  return (
    <>
      <div className="footer">
        <p>This webpage uses concurrency, synchronization, and locking</p>
        <div className="footerInfo">
          <h5 className="info">Owner: Yoshani Gamage</h5>
          <h5 className="info">
            Mail: <a className="mail"href="mailto:yrgamage28@gmail.com">yrgamage28@gmail.com</a>
          </h5>
          <h5 className="info">Undergraduate</h5>
          <h5 className="info">Informatics Institute of Technology</h5>
        </div>
        <div className="socialLinks">
          <a href="www.linkedin.com/in/yoshani-gamage" target="_blank" rel="noopener noreferrer">
            LinkedIn
          </a>
          <a href="https://github.com/yrgamage" target="_blank" rel="noopener noreferrer">
            GitHub
          </a>
          <a href="https://www.instagram.com/yoshani_gamage/profilecard/?igsh=MTJzNGlidWJrNHNxbA==" target="_blank" rel="noopener noreferrer">
            Instagram
          </a>
        </div>
      </div>
    </>

  )
}
